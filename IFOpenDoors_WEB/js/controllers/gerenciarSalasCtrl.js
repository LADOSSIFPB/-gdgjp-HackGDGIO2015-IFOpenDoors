angular.module("IfOpenDoorsApp").controller("gerenciarSalasCtrl", function ($scope, $http, $rootScope, $location) {
  $scope.escolha = {};
  $scope.salas = [];
  $rootScope.app = "Gerenciar Salas";
  $rootScope.show = false;
  $rootScope.shows = '';

  if(!$rootScope.logado){
    $location.url("/");
  }

  $scope.carregarSalas = function () {
    var isOpen = function (sala) {
      $http.get("http://localhost:8080/IFOpenDoors_SERVICE/room/isOpen/"+sala.id).success(function (data) {
        sala.isOpen = data;

        $scope.salas.push(sala);
      })
    };

    var setarAbertura = function (salas) {
      for (var i = 0; i < salas.length; i++) {
        isOpen(salas[i]);
      }
    };

    $http.get("http://localhost:8080/IFOpenDoors_SERVICE/room/all").success(function (data) {
      setarAbertura(data);
    })
  };

  $scope.abrir = function (sala) {
    requisicao = {person:{id:1},
    room:{id:sala}};

    $scope.re = requisicao;

    $http.post("http://localhost:8080/IFOpenDoors_SERVICE/room/open", requisicao).success(function (data) {
      delete $scope.salas;

      $scope.salas = [];
      $scope.carregarSalas();
    });
  };

  $scope.fechar = function (idSala) {
    $http.get("http://localhost:8080/IFOpenDoors_SERVICE/room/close/" + idSala).success(function (data) {
      delete $scope.salas;

      $scope.salas = [];
      $scope.carregarSalas();
    });
  };

  $scope.ok = function (escolha) {
    if(escolha.porta && escolha.nome && escolha.descricao && escolha.tipo){
      return true;
    }
    return false;
  };

  $scope.carregarSalas();
});