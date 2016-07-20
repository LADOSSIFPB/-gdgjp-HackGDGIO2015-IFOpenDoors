angular.module('ifopendoors', []);
angular.module("ifopendoors").controller("ifopendoorsCtrl", function ($scope, $http, $location, $window) {
	$scope.UNAUTHORIZED = false;

    $scope.loginValidation = function (login) {
        console.log(login);
        $http.post("http://localhost:8080/IFOpenDoors_SERVICE/person/login", login).success(function (data) {
          var url = "http://" + $window.location.host + "/gerenciarSalas.html";
          window.location = "/gerenciarSalas.html";
      }).error( function (data, status, headers, config) {
        if(status==401){
            $scope.UNAUTHORIZED = true;
        }
    });
  };

  $().alert('close');
});