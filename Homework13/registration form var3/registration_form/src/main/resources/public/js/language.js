angular.module("language",[])
           .controller("LanguageCtrl", function ($scope, $http) {
               $scope.language = {};
               $scope.sendForm = function(language){
                   $http({
                       method: "POST",
                       url: "/api/language",
                       data: $.param(language),
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