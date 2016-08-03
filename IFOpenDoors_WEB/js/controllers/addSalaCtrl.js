angular.module("IfOpenDoorsApp").controller("addSalaCtrl", function ($scope, $http, $rootScope, $location, appAPI) {
  $scope.escolha = {};
  $rootScope.app = "Adicionar Sala";
  $rootScope.show = false;
  $rootScope.shows = '';
  $rootScope.logado = appAPI.isLogged();

  if(!$rootScope.logado)
    $location.url("/");
  if(appAPI.getRole()!="Professor")
    $location.url("/Salas");

  $rootScope.setRoleInBar();

  var carregarPortas = function () {
    $http.get("http://localhost:8080/IFOpenDoors_SERVICE/door/all").success(function (data) {
      $scope.portas = data;
    }).error(function (data, status) {
      data = "<strong>Ocorreu algum problema ao carregar Portas!</strong> Por favor tente novamente mais tarde.";

      $rootScope.alertERROR(data);
    });
  };

  var carregarTiposSala = function () {
    $http.get("http://localhost:8080/IFOpenDoors_SERVICE/typeroom/all").success(function (data) {
      $scope.tiposSala = data;
    }).error(function (data, status) {
      dataError = "<strong>Ocorreu algum problema ao carregar os tipos de Sala!</strong> Por favor tente novamente mais tarde.";

      $rootScope.alertERROR(dataError);
    });
  };

  $scope.addSala = function (sala) {
    $http.post("http://localhost:8080/IFOpenDoors_SERVICE/room/insert", sala).success(function (data) {
      delete $scope.escolha;

      dataSucess = "<strong>Sala adcionada com sucesso!</strong>";
      var button ={
        data:"Adicionar outra",
        location:"AddSala"
      };

      $rootScope.alertSUCESS(dataSucess, button);
    }).error(function (data, status) {
      dataError = "<strong>Ocorreu algum problema ao adicionar Sala!</strong> Por favor tente novamente mais tarde.";

      $rootScope.alertERROR(dataError);
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