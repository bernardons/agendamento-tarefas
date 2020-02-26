'use strict';

// Declare app level module which depends on views, and core components
angular.module('myApp', [
  'ui.router',
  'angular.filter',
  'ui.bootstrap',
  'ngRoute',
  'myApp.home',
]).
config(['$locationProvider', '$routeProvider', '$stateProvider', function($locationProvider, $routeProvider, $stateProvider, $urlRouterProvider) {
  $locationProvider.hashPrefix('');

  $stateProvider.state('home', {
                 url: "/home",
                 controller: 'HomeCtrl',
                 templateUrl: "home/home.html"
               });
  $routeProvider.when('/home', {
    templateUrl: 'home/home.html',
    controller: 'HomeCtrl'
  }).otherwise({redirectTo: '/home'});
}]);
