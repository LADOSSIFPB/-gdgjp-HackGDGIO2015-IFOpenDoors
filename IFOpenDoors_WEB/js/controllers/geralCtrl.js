angular.module("IfOpenDoorsApp").controller("geralCtrl", function ($scope, $http, appAPI, $rootScope) {
  $scope.app = "IfOpenDoors";
  $rootScope.logado = false;
  $scope.salasOpcaos = [{name:'Gerenciar', link:'Salas'}, {name:'Adicionar', link:'AddSala'}];
  $scope.pessoasOpcaos = [{name:'Adicionar', link:'AddPessoa'}];

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