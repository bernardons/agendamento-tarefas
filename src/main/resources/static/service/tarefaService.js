angular.module('TarefaService', [])

.factory('TarefaService', ['$http', function ($http) {

  var urlBase = 'http://localhost:8080/desafio';

  return {
      salvar: function(tarefa) {
            return $http.post(urlBase + '/tarefa', tarefa).then(function(response) {
                return response.data;
            });
      },
      listar: function(){
        return $http.get(urlBase + '/tarefa').then(function(response) {
            return response.data;
        });
      },
      executar: function(id){
        return $http.get(urlBase + '/tarefa/' + id).then(function(response) {
            return response.data;
        });
      }
  }

}]);


//6513BF4C88F7A2364ACE249373CDE0A7
//A859C75418E24241FFBE4797FA4607B0
