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

package marcosambrosi.mmovies.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import marcosambrosi.mmovies.adapter.MoviesAdapter;
import marcosambrosi.mmovies.MoviesApplication;
import marcosambrosi.mmovies.R;
import marcosambrosi.mmovies.model.Configuration;
import marcosambrosi.mmovies.model.MovieResponse;
import marcosambrosi.mmovies.network.ServiceController;
import marcosambrosi.mmovies.util.Constants;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen);


        if (MoviesApplication.getInstance().hasConfiguration()) {
            String configString = MoviesApplication.getInstance().
                    getPreference(Constants.PREFERENCES.CONFIG);

            Configuration configuration = Configuration.fromJsonString(configString);

            MoviesApplication.getInstance().setConfiguration(configuration);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    listOnCreate();
                }
            }, 2000);
        } else {
            ServiceController.getInstance().configuration(new Callback<Configuration>() {
                @Override
                public void success(Configuration configuration, Response response) {
                    MoviesApplication.getInstance().addPreference(Constants.PREFERENCES.CONFIG,
                            configuration.toJsonString());
                    MoviesApplication.getInstance().setConfiguration(configuration);
                    listOnCreate();
                }

                @Override
                public void failure(RetrofitError error) {
                    //Handle this
                }
            });
        }


    }

    private void listOnCreate() {
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewMovies = (RecyclerView) findViewById(R.id.recycler_view_movies);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                this,
                2,
                GridLayoutManager.VERTICAL,
                false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position % 3 == 0 ? 2 : 1);
            }
        });
        recyclerViewMovies.setLayoutManager(gridLayoutManager);

        final MoviesAdapter adapter = new MoviesAdapter();

        recyclerViewMovies.setAdapter(adapter);

        ServiceController.getInstance().discoverMovies(new Callback<MovieResponse>() {
            @Override
            public void success(MovieResponse movieResponse, Response response) {
                if (movieResponse != null) {
                    adapter.addAll(movieResponse.movies);
                }
            }

            @Override
            public void failure(RetrofitError error) {

                if (error != null) {

                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
