angular.module("IfOpenDoorsApp").controller("geralCtrl", function ($scope, $http, appAPI) {
  $scope.app = "IfOpenDoors";
  $scope.salasOpcaos = [{name:'Gerenciar', link:'Salas'}, {name:'Adicionar', link:'AdicionarSala'}];
  $scope.pessoasOpcaos = ['Gerenciar', 'Adicionar'];

  $scope.logado = function () {
    return true;
  };

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