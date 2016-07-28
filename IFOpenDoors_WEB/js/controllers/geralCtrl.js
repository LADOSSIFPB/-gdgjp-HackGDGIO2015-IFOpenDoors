angular.module("IfOpenDoorsApp").controller("geralCtrl", function ($scope, $http, appAPI, $rootScope) {
  $scope.app = "IfOpenDoors";
  $rootScope.logado = false;
  $scope.salasOpcaos = [{name:'Gerenciar', link:'Salas'}, {name:'Adicionar', link:'AddSala'}];
  $scope.pessoasOpcaos = [{name:'Adicionar', link:'AddPessoa'}];

  $scope.barra = function () {
    $rootScope.show = !$scope.show;
    $rootScope.shows = '';
  };

  $scope.salasOptions = function () {
    $rootScope.shows = 'sala';
  };

  $scope.pessoasOptions = function () {
    $rootScope.shows = 'pessoa';
  };
});