"use strict";

(function () {

    angular.module("cinema").service("movieDal", ["dal", MovieDal]);

    function MovieDal (dal) {

        this.getMovies = function () {
            return dal.http.GET("rest/cd/json");
        };

        this.saveMovie = function (movieToSave) {
            return dal.http.POST("rest/cd/json", movieToSave);
        };

        this.updateMovie = function (movieToUpdate) {
            return dal.http.PUT("rest/cd/json/", movieToUpdate);
        };

        this.deleteMovie = function (movieToDelete) {
            return dal.http.DELETE("rest/cd/json/", movieToDelete);
        };

    }
}());
