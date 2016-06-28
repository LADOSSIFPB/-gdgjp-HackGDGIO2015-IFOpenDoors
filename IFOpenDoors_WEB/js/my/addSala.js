angular.module('ifopendoors', []);
angular.module("ifopendoors").controller("ifopendoorsCtrl", function ($scope) {
  $scope.escolha = {};
  $scope.salasOpcaos = ['Gerenciar', 'Adicionar'];
  $scope.pessoasOpcaos = ['Gerenciar', 'Adicionar'];
  $scope.portas = [
    {numero:1, ip:"192.168.0.34"},
    {numero:4, ip:"192.168.2.24"},
    {numero:7, ip:"192.168.0.201"},
    {numero:19, ip:"192.168.1.32"}
  ];  

  $scope.barra = function () {
    $scope.show = !$scope.show;
  };

  $scope.salasOptions = function () {
    $scope.shows = 'sala';
  };

  $scope.pessoasOptions = function () {
    $scope.shows = 'pessoa';
  };

  $scope.ok = function (escolha) {
    if(escolha.porta && escolha.nome && escolha.descricao && escolha.tipo){
      return true;
    }
    return false;
  };
});