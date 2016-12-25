namespace erfassung {
     'use strict';

     class Config {
         constructor(private $stateProvider: angular.ui.IStateProvider){
             //constructor(){
                //  this.$stateProvider.state("/", {
                     
                //  })
         }

     }

     angular.module('erfassung')
     //.config([() =>{ return new Config()}]);
     .config(["$stateProvider", 
        ($stateProvide: angular.ui.IStateProvider) => { 
            return new Config($stateProvide);
        }
    ]);

}