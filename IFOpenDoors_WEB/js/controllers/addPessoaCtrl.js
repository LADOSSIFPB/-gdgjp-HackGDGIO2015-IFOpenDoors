angular.module("IfOpenDoorsApp").controller("addPessoaCtrl", function ($scope, $http, $rootScope, $location, $cookies, appAPI) {
  $rootScope.app = "Adicionar Pessoa"
  $scope.escolha = {};
  $rootScope.show = false;
  $rootScope.shows = '';
  $rootScope.logado = appAPI.isLogged();

  if(!$rootScope.logado)
    $location.url("/");
  if(appAPI.getRole()!="Professor")
    $location.url("/Salas");

  $rootScope.setRoleInBar();

  $scope.cpf= function(cpf) {
    cpf = cpf.replace(/[^0-9]/g,'')

    if(cpf.length>3)
      cpf = cpf.substring(0, 3) + '.' + cpf.substring(3);
    if(cpf.length>7)
      cpf = cpf.substring(0, 7) + '.' + cpf.substring(7);
    if(cpf.length>11)
      cpf = cpf.substring(0, 11) + '-' + cpf.substring(11,13);

    $scope.pessoa.cpf = cpf;
  };

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

  $scope.ok = function (pessoa) {
    if (pessoa.name && pessoa.cpf && pessoa.email && pessoa.password)    {
      return false;
    }
    return true;
  };

  carregarRoles();
});