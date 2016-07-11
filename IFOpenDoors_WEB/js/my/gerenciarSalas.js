angular.module('ifopendoors', []);
angular.module("ifopendoors").controller("ifopendoorsCtrl", function ($scope) {
  $scope.escolha = {};
  $scope.salasOpcaos = ['Gerenciar', 'Adicionar'];
  $scope.pessoasOpcaos = ['Gerenciar', 'Adicionar'];
  $scope.salas = [
    {nome:"Info 1", tipoSala:{name:"Laboratorio"}, descricao:"Lugar para programar", aberto:false},
    {nome:"Sala 1", tipoSala:{name:"Sala"}, descricao:"Aulas expositivas", aberto:true},
    {nome:"Sala 23", tipoSala:{name:"Sala"}, descricao:"Aulas expositivas", aberto:false},
    {nome:"Meterologia 1", tipoSala:{name:"Laboratorio"}, descricao:"Aulas de PeG", aberto:true},
    {nome:"Sala 13", tipoSala:{name:"Sala"}, descricao:"Lugar para programar", aberto:true},
    {nome:"Eletronica", tipoSala:{name:"Laboratorio"}, descricao:"Aulas do curso de Engenharia", aberto:false}
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