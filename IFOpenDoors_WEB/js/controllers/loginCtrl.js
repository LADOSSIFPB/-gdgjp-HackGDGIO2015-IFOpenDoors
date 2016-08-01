angular.module("IfOpenDoorsApp").controller("loginCtrl", function ($scope, $http, $location, appAPI, $rootScope, $cookies, $sce) {
	$scope.ERROR = {
    ative:false,
    data:""
  };

  $rootScope.app = "IfOpenDoors";
  $rootScope.logado = appAPI.isLogged();

  $scope.setRoleInBar = function(data){
    $rootScope.salasOpcaos = [];
    $rootScope.pessoasOpcaos = [];

    if(data.role.name == "Professor"){
      $rootScope.role = "Professor";
      $rootScope.salasOpcaos.push({name:'Gerenciar', link:'Salas'}, {name:'Adicionar', link:'AddSala'});
      $rootScope.pessoasOpcaos.push({name:'Adicionar', link:'AddPessoa'});
    }

    if(data.role.name == "Aluno"){
      $rootScope.role = "Aluno";
      $rootScope.salasOpcaos.push({name:'Gerenciar', link:'Salas'});
    }
  }

  if($rootScope.logado){
    $scope.setRoleInBar($cookies.getObject('user'));
    $location.url("/Salas");
  }

  $scope.toTrustedHTML = function(html){
    return $sce.trustAsHtml( html );
  }

  $scope.loginValidation = function (login) {
    $http.post("http://localhost:8080/IFOpenDoors_SERVICE/person/login", login).success(function (data) {
      $scope.ERROR.ative = false;
      $scope.ERROR.data = "";

      appAPI.login(data);
      $rootScope.logado = appAPI.isLogged();

      $scope.setRoleInBar(data);

      $location.url("Salas");
    }).error( function (data, status, headers, config) {
      if(status==401)
        $scope.ERROR.data = "<strong>Acesso Negado!</strong> Email ou senha incorreto.";
      else
        $scope.ERROR.data = "<strong>Ocorreu algum problema!</strong> Por favor tente novamente mais tarde.";

      $scope.ERROR.ative = true;
    });
  };

  $scope.closeAlert = function () {
    $scope.ERROR.ative = false;
  };
});