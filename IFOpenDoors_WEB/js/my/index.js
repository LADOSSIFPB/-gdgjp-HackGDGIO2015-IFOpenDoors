angular.module('ifopendoors', []);
angular.module("ifopendoors").controller("ifopendoorsCtrl", ['$scope', '$http', function($scope, $http) {
	$scope.error = false;

	$scope.loginValidation = function (login) {        
        var config = {
            headers : {
                'Content-Type': 'application/json'
            }
        }

        $http.post('http://127.0.0.1:8080/IFOpenDoors_SERVICE/person/login', login, config)
        .success(function (data, status, headers, config) {
            $location.path('adiconarSala.html');
        })
        .error(function (data, status, header, config) {
            $scope.error = true;
        });
    };
}]);