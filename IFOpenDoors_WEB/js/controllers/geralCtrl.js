angular.module("IfOpenDoorsApp").controller("geralCtrl", function ($scope, $http) {
  $scope.logado = false;
  $scope.app = "IfOpenDoors";
  $scope.salasOpcaos = [{name:'Gerenciar', link:'Salas'}, {name:'Adicionar', link:'AdicionarSala'}];
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