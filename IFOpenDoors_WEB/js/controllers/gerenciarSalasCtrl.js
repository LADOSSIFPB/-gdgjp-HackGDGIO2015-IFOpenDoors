angular.module('ifopendoors', []);
angular.module("ifopendoors").controller("ifopendoorsCtrl", function ($scope, $http) {
  $scope.escolha = {};
  $scope.salasOpcaos = ['Gerenciar', 'Adicionar'];
  $scope.pessoasOpcaos = ['Gerenciar', 'Adicionar'];
  $scope.salas = [];

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
    });

    delete $scope.salas;

    $scope.salas = [];
    $scope.carregarSalas();
  };

  $scope.fechar = function (idSala) {
    $http.get("http://localhost:8080/IFOpenDoors_SERVICE/room/close/" + idSala).success(function (data) {
    });

    delete $scope.salas;

    $scope.salas = [];
    $scope.carregarSalas();
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
    if(escolha.porta && escolha.nome && escolha.descricao && escolha.tipo){
      return true;
    }
    return false;
  };

  $scope.carregarSalas();
});