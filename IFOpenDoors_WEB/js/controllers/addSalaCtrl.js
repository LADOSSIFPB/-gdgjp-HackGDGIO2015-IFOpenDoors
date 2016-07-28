angular.module("IfOpenDoorsApp").controller("addSalaCtrl", function ($scope, $http, $rootScope) {
  $scope.escolha = {};
  $rootScope.app = "Adicionar Salas";

  if(!$rootScope.logado){
    $location.url("/");
  }

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

  $scope.ok = function (escolha) {
    if(escolha.door && escolha.nome && escolha.descricao && escolha.tipoSala){
      return true;
    }
    return false;
  };

  carregarPortas();
  carregarTiposSala();
});