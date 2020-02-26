
'use strict';
	angular.module('myApp.home', ['TarefaService']).controller('HomeCtrl', ['$scope', 'TarefaService', '$state', function($scope, TarefaService, $state) {
	
		$scope.tarefas = [];
		$scope.tarefa = {tabelaOrigem:'TB_PESSOA_LEFT',tabelaDestino:'TB_PESSOA_RIGHT'};
		$scope.tabelas = ['TB_PESSOA_RIGHT','TB_PESSOA_LEFT'];

		$scope.loadContext = function(){
			$scope.listar();

		}

		$scope.listar = function(){
			TarefaService.listar().then(function(result) {
        $scope.tarefas = result;
      }, function(error) {
         $scope.errorMsg = error.data.message;
       });
		}

		$scope.salvar = function(){
			TarefaService.salvar($scope.tarefa).then(function(result) {
        $scope.listar();
				$("#addTarefaModal .close").click();
      }, function(error) {
         $scope.details = error.data.details;
       });
		}

		$scope.executarTarefa = function(id){
			TarefaService.executar(id).then(function(result) {
        $scope.listar();
      }, function(error) {
         $scope.errorMsg = error.data.message;
       });
		}
		$scope.loadContext();


}]);
