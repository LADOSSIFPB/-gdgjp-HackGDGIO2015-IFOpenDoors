angular.module("IfOpenDoorsApp").controller("ListagemEspCtrl", function ($scope, $http, $rootScope, $location, $cookies, appAPI) {
  $rootScope.app = "Dispositivos"
  $scope.escolha = {};
  $scope.esps=[
  {ip:"192.168.1.1",id:"1",status:"Online", descricao:"Prog 1"},
  {ip:"192.168.1.2",id:"2",status:"Offline", descricao:"Prog 2"},
  {ip:"192.168.1.3",id:"3",status:"Ocupado", descricao:"Prog 3"}
  ];
  $rootScope.show = false;
  $rootScope.shows = '';
  $rootScope.logado = appAPI.isLogged();

  if(!$rootScope.logado)
    $location.url("/");
  if(appAPI.getRole()!="Professor")
    $location.url("/Salas");

  $rootScope.setRoleInBar();

  var carregarRoles = function () {
    $http.get("http://localhost:8080/IFOpenDoors_SERVICE/role/all").success(function (data) {
      $scope.roles = data;
    }).error(function (data, status) {
      dataError = "<strong>Ocorreu algum problema ao carregar os tipos de Pessoa!</strong> Por favor tente novamente mais tarde.";

      $rootScope.alertERROR(dataError);
    });
  };

  $scope.addPessoa = function (pessoa) {
    console.log(pessoa);

    $http.post("http://localhost:8080/IFOpenDoors_SERVICE/person/insert", pessoa).success(function (data) {
      delete $scope.pessoa;
      delete $scope.senhaVer;

      dataSucess = "<strong>Pessoa adcionada com sucesso!</strong>";
      var button ={
        data:"Adicionar outra",
        location:"AddPessoa"
      };

      $rootScope.alertSUCESS(dataSucess, button);
    }).error(function (data, status) {
      dataError = "<strong>Ocorreu algum problema ao adicionar Pessoa!</strong> Por favor tente novamente mais tarde.";

      $rootScope.alertERROR(dataError);
    });
  };


  carregarRoles();
});