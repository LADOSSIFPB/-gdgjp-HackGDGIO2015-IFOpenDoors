angular.module('ifopendoors', []);
angular.module("ifopendoors").controller("ifopendoorsCtrl", function ($scope) {
  $scope.escolha = {};
  $scope.salasOpcaos = ['Gerenciar', 'Adicionar'];
  $scope.pessoasOpcaos = ['Gerenciar', 'Adicionar'];
  $scope.pessoas = [
  {nome:"Rhavy",cpf:"123", tipo:"professor", email:"rhavy@hotmail.com", senha:"123"}
  ]; 

  $scope.adicionarPessoa= function(pessoa) {
    $scope.pessoas.push ({pessoa});
    delete $scope.pessoa;
    return true;
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

  $scope.ok = function (pessoa) {
    if (pessoa.nome && pessoa.tipo && pessoa.cpf && pessoa.email && pessoa.senha)    {
      return false;
    }
      return true;
  };
});