angular.module("IfOpenDoorsApp").controller("geralCtrl", function ($scope, $http) {
  $scope.app = "IfOpenDoors";
  $scope.salasOpcaos = ['Gerenciar', 'Adicionar'];
  $scope.pessoasOpcaos = ['Gerenciar', 'Adicionar'];

  $scope.barra = function () {
    $scope.show = !$scope.show;
  };

  $scope.salasOptions = function () {
    $scope.shows = 'sala';
  };

  $scope.pessoasOptions = function () {
    $scope.shows = 'pessoa';
  };
});