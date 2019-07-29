package com.guidance.moviesapp.api;


public class TheMovieDBAPI {

    private static String API_KEY = "4a7d2c349f439f8ac8d00d0bff4b7e32";

    public static String baseURL = "https://api.themoviedb.org/3/";

    public static String popularMoviesURL = baseURL + "movie/popular?api_key=" + API_KEY;
    public static String topRatedMoviesURL = baseURL + "movie/top";


}
