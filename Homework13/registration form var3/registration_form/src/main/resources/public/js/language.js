angular.module("language",[])
           .controller("LanguageCtrl", function ($scope, $http) {
               $scope.auth = {};
               $scope.sendForm = function(auth){
                   $http({
                       method: "POST",
                       url: "/",
                       data: $.param(auth),
                       headers: { "Content-Type" : "application/x-www-form-urlencoded" }
                   }).then(
                       function(data) {
                           window.alert("Успешно выбран язык");
                       },
                       function(error) {
                           window.alert("При выборе языка произошла ошибка");
                       }
                   );
               }
           });