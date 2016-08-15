angular.module("IfOpenDoorsApp").controller("geralCtrl", function ($scope, $http, appAPI, $rootScope, $location, $cookies) {
  $rootScope.app = "IfOpenDoors";
  $rootScope.logado = appAPI.isLogged();
  $rootScope.salasOpcaos = [];
  $rootScope.pessoasOpcaos = [];

  $rootScope.ERROR = {
    ative:false,
    data:"",
    button:{
      ative:false
    }
  };

  $rootScope.SUCESS = {
    ative:false,
    data:""
  };

  $rootScope.setRoleInBar = function(){
    var data = $cookies.getObject('user');

    $rootScope.salasOpcaos = [];
    $rootScope.pessoasOpcaos = [];

    if(data.role.name == "Professor"){
      $rootScope.role = "Professor";
      $rootScope.salasOpcaos.push({name:'Gerenciar', link:'Salas'}, {name:'Adicionar', link:'AddSala'},{name:'Dispositivos', link:'ListagemEsp'});
      $rootScope.pessoasOpcaos.push({name:'Adicionar', link:'AddPessoa'});
    }

    if(data.role.name == "Aluno"){
      $rootScope.role = "Aluno";
      $rootScope.salasOpcaos.push({name:'Gerenciar', link:'Salas'});
    }
  }

  $scope.barra = function () {
    $rootScope.show = !$scope.show;
    $rootScope.shows = '';
  };

  $scope.logout = function () {
    appAPI.logout();
  };

  $scope.isLogged = function () {
    return appAPI.isLogged();
  };

  $scope.salasOptions = function () {
    $rootScope.shows = 'sala';
  };

  $scope.pessoasOptions = function () {
    $rootScope.shows = 'pessoa';
  };

  $scope.closeAlertERROR = function () {
    $rootScope.ERROR.ative = false;
  };

  $scope.closeAlertSUCESS = function () {
    $rootScope.SUCESS.ative = false;
  };

  $rootScope.alertERROR = function (data) {
    $rootScope.ERROR.ative = true;
    $rootScope.SUCESS.ative = false;

    $rootScope.ERROR.data = data;
  };

  $rootScope.alertSUCESS = function (data) {
    $rootScope.SUCESS.ative = true;
    $rootScope.ERROR.ative = false;

    $rootScope.SUCESS.data = data;

    $location.url("/Salas");
  };

  $rootScope.alertSUCESS = function (data, button) {
    $rootScope.SUCESS.ative = true;
    $rootScope.ERROR.ative = false;
    button.ative = true;

    $rootScope.SUCESS.button = button;
    $rootScope.SUCESS.data = data;

    $location.url("/Salas");
  };

  $rootScope.redirect = function (location) {
    $rootScope.ERROR.ative = false;
    $rootScope.SUCESS.ative = false;

    $location.url(location);
  };
});