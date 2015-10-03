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
import marcosambrosi.mmovies.model.MovieResponse;
import marcosambrosi.mmovies.util.Constants;
import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by marcosambrosi on 5/6/15.
 */
public class ServiceController {

    private static ServiceController sInstance;
    private static MoviesService sService;

    private static String sAuthToken;

    static {
        initializeService();
    }

    private ServiceController() {
    }

    private static void initializeService() {

        RestAdapter.Builder restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL);


        sService = restAdapter.build().create(MoviesService.class);
    }

    public static ServiceController getInstance() {
        if (sInstance == null) {
            sInstance = new ServiceController();
        }
        return sInstance;
    }

    public void discoverMovies(int page, Callback<MovieResponse> callback) {
        sService.discoverMovie(page, callback);
    }

    public void configuration(Callback<Configuration> callback) {
        sService.configuration(Constants.API_KEY, callback);
    }
}
