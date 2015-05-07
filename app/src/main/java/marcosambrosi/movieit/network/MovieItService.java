package marcosambrosi.movieit.network;

import marcosambrosi.movieit.model.MovieResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by marcosambrosi on 5/6/15.
 */
public interface MovieItService {

    @GET("/discover/movie")
    void discoverMovie(@Path("page") int page, Callback<MovieResponse> callback);
}
