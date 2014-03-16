app.factory('basketService',['$http', function($http) {
    return {
        getBasket: function(){
            return $http.get('/service/basket/');
        },

        addMonster: function(monsterType){
            return $http.post('/service/basket/' + encodeURIComponent(monsterType));
        },

        removeMonster: function(monsterType){
            return $http.delete('/service/basket/' + encodeURIComponent(monsterType));
        },

        basketSum: function(){
            return $http.get('/service/basket/sum');
        }
    };
}]);
