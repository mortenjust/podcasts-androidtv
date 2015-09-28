package com.example.mortenjust.deadline;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieList {
    String TAG = "mj.store";
    Context context;

    public static List<Movie> list;

    public MovieList(Context context){
        this.context = context;
    }

    public void setupMovies(final String url, final OnTvResponseListener listener) {
        list = new ArrayList<>(); // keep an invisible list of everyhinh for later

        //String url = "http://www.dr.dk/mu/Feed/deadline?format=podcast&limit=500";
        SimpleXmlRequest<TvShow> showRequest = new SimpleXmlRequest<TvShow>(Request.Method.GET, url, TvShow.class,
                new Response.Listener<TvShow>() {
                    @Override
                    public void onResponse(TvShow tvShow) {
                        Log.d(TAG, "Let's get some data for " + tvShow.getShowTitle());

                        List<Movie> showList = new ArrayList<>();


                        // add all episodes to a list
                        for(TvShow.Episode e : tvShow.getAllEpisodes()){
                            ///Log.d(TAG, "Found episode: "+e.getVideoLink());
                            Movie newMovie = buildMovieInfo(
                                    "Deadline",
                                    e.getEpisodeTitle(),
                                    e.getDescription(),
                                    tvShow.getCopyright(),
                                    e.getVideoLink(),
                                    tvShow.getShowImage(),
                                    tvShow.getShowImage());

                            list.add(newMovie); // global list of all episodes
                            showList.add(newMovie); // just this one show

                        }

                        // We're done! Send just this one show back to the listener
                        // the getNewest is so we can sort

                        listener.hereAreTheMovies(showList, tvShow.getNewestDateTime());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "error here "+error.getLocalizedMessage());
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(showRequest);

        return;
    }

    private static Movie buildMovieInfo(String category, String title,
                                        String description, String studio, String videoUrl, String cardImageUrl,
                                        String bgImageUrl) {
        Movie movie = new Movie();
        movie.setId(Movie.getCount());
        Movie.incCount();
        movie.setTitle(title);
        movie.setDescription(description);
        movie.setStudio(studio);
        movie.setCategory(category);
        movie.setCardImageUrl(cardImageUrl);
        movie.setBackgroundImageUrl(bgImageUrl);
        movie.setVideoUrl(videoUrl);
        return movie;
    }
}
