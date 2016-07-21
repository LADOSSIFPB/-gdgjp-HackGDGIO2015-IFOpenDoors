angular.module('ifopendoors', []);
angular.module("ifopendoors").controller("ifopendoorsCtrl", function ($scope, $http, $location) {
	$scope.UNAUTHORIZED = false;

    $scope.loginValidation = function (login) {
        $http.post("http://localhost:8080/IFOpenDoors_SERVICE/person/login", login).success(function (data) {
          $scope.UNAUTHORIZED = false;

          window.location.replace("http://" + location.host + "/gerenciarSalas.html");
      }).error( function (data, status, headers, config) {
        if(status==401){
            $scope.UNAUTHORIZED = true;
        }
    });
  };

  $scope.closeAlert = function () {
    $scope.UNAUTHORIZED = false;
};
});