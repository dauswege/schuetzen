namespace erfassung{
    'use strict';

    interface Duration {
        constructor(seconds: number, negative: boolean, zero: boolean, nano: number, units: string[]);
    }

    interface Position{
        constructor(description: string, duration: Duration);    
    }

    class ErfassungController{

        static $inject = ['$http', '$filter'];

        testString: string = "gogo";
        person: any;
        positions:  Array<Position>;
        newPosition: any;
        
        constructor(private $http: ng.IHttpService, private $filter: ng.IFilterService){
            
        }

        $onInit(){
            this.$http.get("persons/search/findMyPerson").then((result)=>{
                this.person = result.data;
            });
            this.$http.get("positions/search/findMyPositions").then((result: any)=> {
                this.positions = result.data._embedded.positions;
            })
            // this.$http.get("positions/search/findMyPositionsMapped").then((result) => {
            //     console.log(result.data);
            // });
        }

        addBooking(){
            // console.log(this.$filter("date")(this.newPosition.bookingDay.bookingDate, "yyyy-MM-dd"));
            // this.newPosition.bookingDay.bookingDate = this.$filter("date")(this.newPosition.bookingDay.bookingDate, "yyyy-MM-dd");
            console.log(this.newPosition);
            console.log(this.person);
            console.log(this.person._links.bookingDays.href);
            this.$http.get(this.person._links.bookingDays.href).then(result => {
                console.log(result);
            });
            // this.$http.post(this.person._links.bookingDays.href+"", {"bookingDay": this.newPosition.bookingDay}).then(result =>{
            //     console.log("sent!");
            // });
            this.$http.post("bookingDays", {"bookingDay": this.newPosition.bookingDay, "person": { "href": this.person._links.self.href}});

        }

    }

    angular.module('erfassung').controller('ErfassungController', ErfassungController)

}