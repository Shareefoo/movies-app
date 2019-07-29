package com.guidance.moviesapp.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.guidance.moviesapp.R;
import com.guidance.moviesapp.adapters.MovieAdapter;
import com.guidance.moviesapp.api.TheMovieDBAPI;
import com.guidance.moviesapp.api.VolleySingleton;
import com.guidance.moviesapp.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "VOLLEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPopularMovies();
    }

    private void getPopularMovies() {
        // Get popular movies
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, TheMovieDBAPI.popularMoviesURL,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //
                                try {

                                    List<Movie> movieList = new ArrayList<>();

                                    JSONArray moviesArray = response.getJSONArray("results");
                                    for (int i = 0; i < moviesArray.length(); i++) {
                                        JSONObject movieObject = moviesArray.getJSONObject(i);
                                        String postPath = movieObject.getString("poster_path");
                                        String title = movieObject.getString("title");
                                        //
                                        Log.d(TAG, "onResponse: Title: " + title);

                                        Movie movie = new Movie();
                                        movie.setPoster_path(postPath);
                                        movieList.add(movie);
                                    }

//                                    Adapter adapter = new MovieAdapter(movieList);
//                                    recycler.setAdapter(adapter);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "onErrorResponse: " + error.toString());
                                Log.d(TAG, "onErrorResponse: " + error.getMessage());
                            }
                        });

        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest, "REQUEST_TAG");
    }

    private void getTopMovies() {
        //   
    }

}
