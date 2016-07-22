angular.module('ifopendoors', []);
angular.module("ifopendoors").controller("ifopendoorsCtrl", function ($scope, $http) {
  $scope.escolha = {};
  $scope.salasOpcaos = ['Gerenciar', 'Adicionar'];
  $scope.pessoasOpcaos = ['Gerenciar', 'Adicionar'];

  var carregarPortas = function () {
    $http.get("http://localhost:8080/IFOpenDoors_SERVICE/door/all").success(function (data) {
      $scope.portas = data;
    }).error(function (data, status) {
      $scope.message = "Aconteceu um problema: " + data;
    });
  };

  var carregarTiposSala = function () {
    $http.get("http://localhost:8080/IFOpenDoors_SERVICE/typeroom/all").success(function (data) {
      $scope.tiposSala = data;
    }).error(function (data, status) {
      $scope.message = "Aconteceu um problema: " + data;
    });
  };

  $scope.addSala = function (sala) {
    $http.post("http://localhost:8080/IFOpenDoors_SERVICE/room/insert", sala).success(function (data) {
      delete $scope.escolha;
    });
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

  $scope.ok = function (escolha) {
    if(escolha.door && escolha.nome && escolha.descricao && escolha.tipoSala){
      return true;
    }
    return false;
  };

  carregarPortas();
  carregarTiposSala();
});