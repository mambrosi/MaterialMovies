/*
 *
 *
 *  *
 *  *  * Copyright (C)  2015 Marcos Ambrosi marcosambrosi@gmail.com
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *  *
 *
 */

package marcosambrosi.mmovies.network;

import marcosambrosi.mmovies.model.Configuration;
import marcosambrosi.mmovies.model.response.MovieResponse;
import marcosambrosi.mmovies.model.response.ReviewResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by marcosambrosi on 5/6/15.
 */
public interface MoviesService {

    @GET("/discover/movie")
    void discoverMovie(@Query("api_key") String apiKey, Callback<MovieResponse> callback);

    @GET("/movie/{id}/reviews")
    void getMovieReviews(@Query("api_key") String apiKey, @Path("id") String movieId,
                         Callback<ReviewResponse> callback);

    @GET("/configuration")
    void configuration(@Query("api_key") String apiKey, Callback<Configuration> callback);


}
