namespace erfassung{
    'use strict';

    interface Duration {
        constructor(seconds: number, negative: boolean, zero: boolean, nano: number, units: string[]);
    }

    interface Position{
        constructor(description: string, activity: string, duration: Duration);    
    }

    interface SavePosition{
        constructor(description: string, activity: string, duration: string, date: Date);
    }

    class SavePositionDTO<SavePosition>{
        constructor(private description: string, private activity: string, private duration: string, private date: Date){
        };
    }

    interface BookingDay{
        constructor(bookingDate: Date, positions: Array<Position>);
    }

    class ErfassungController{

        static $inject = ['$http', '$filter'];

        testString: string = "gogo";
        person: any;
        positions:  Array<Position>;
        bookingDays: Array<BookingDay>;
        newPosition: any;
        
        constructor(private $http: ng.IHttpService, private $filter: ng.IFilterService){
            
        }

        $onInit(){
            this.getBookingDays();
            this.newPosition = {};
            this.newPosition.date = new Date();
        }

        deleteBooking(bookingDay, toDelete){
            
            var positionIndex = bookingDay.positions.indexOf(toDelete);
            
            this.$http.get(bookingDay._links.positions.href).then(result => {
                var data: any = result.data;
                var posLinkToDelete = data._embedded.positions[positionIndex]._links.self.href;
                
                this.$http.delete(posLinkToDelete).then(result => this.getBookingDays());
            });

        }

        addBooking(){
            // console.log(this.newPosition);
            
            var time = "PT" + this.$filter('date')(this.newPosition.duration, "HH") + "H" + this.$filter('date')(this.newPosition.duration, "mm") + "M"  ;

            var positionToSave: any = {
                "description": this.newPosition.description,
                "activity": this.newPosition.activity,
                "date": this.newPosition.date,
                "duration": time
            };
            this.$http.post("addPosition", positionToSave).then((result)=>{
                // console.log(result.data);
                this.newPosition = {};
                this.newPosition.date = new Date();
                this.getBookingDays();
            })

        }

        formatDuration(durationString: string){

            if(durationString == undefined){
                return "";
            }
            var formattedDuration = "";
            var resu = durationString.search("H");
            if(durationString.search("H") >= 0){
                var hours = +durationString.substring(2, durationString.indexOf("H"));
                if(hours<10){
                    formattedDuration = "0";
                }
                formattedDuration = formattedDuration + hours;
            } else {
                formattedDuration = "00";
            }
            formattedDuration = formattedDuration + ":";
            

            if(durationString.search("M") >= 0){
                var minutes;
                if(durationString.search("H") >= 0){
                    minutes = +durationString.substring(durationString.indexOf("H")+1, durationString.indexOf("M"));
                } else {
                    minutes = +durationString.substring(durationString.indexOf("PT")+2, durationString.indexOf("M"));
                }

                if(minutes < 10){
                    formattedDuration = formattedDuration + "0";
                }
                formattedDuration = formattedDuration + minutes;
            } else {
                formattedDuration = formattedDuration + "00";
            }

            return formattedDuration;
            

        }

        private getBookingDays(){
            this.getBookingDays_().then(result => {});
        }

        private getBookingDays_(){
            return this.$http.get("bookingDays/search/findMyBookingDays?projection=bookingdaysWithPositionsInline").then((result: any) => {
                this.bookingDays = result.data._embedded.bookingDays;
            });
        }

    }

    angular.module('erfassung').controller('ErfassungController', ErfassungController)

}