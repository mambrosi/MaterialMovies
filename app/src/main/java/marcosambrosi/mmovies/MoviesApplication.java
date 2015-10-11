package marcosambrosi.mmovies;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import marcosambrosi.mmovies.model.Configuration;
import marcosambrosi.mmovies.network.ServiceController;
import marcosambrosi.mmovies.util.Constants;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by marcosambrosi on 10/3/15.
 */
public class MoviesApplication extends Application {


    private SharedPreferences mSharedPreferences;


    @Override
    public void onCreate() {
        super.onCreate();

        mSharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        checkIfCallConfigurationServiceNeeded();
    }

    private void checkIfCallConfigurationServiceNeeded() {
        ServiceController.getInstance().configuration(new Callback<Configuration>() {
            @Override
            public void success(Configuration configuration, Response response) {
                if (configuration != null) {
                    addPreference(Constants.PREFERENCES.CONFIG,
                            new Gson().toJson(configuration,
                                    Configuration.class));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (error != null) {

                }
            }
        });
    }


    public void addPreference(String key, String value) {
        if (mSharedPreferences != null) {
            mSharedPreferences.edit().putString(key, value).apply();
        }
    }


    @Override
    public void onTerminate() {
        super.onTerminate();

        mSharedPreferences = null;
    }
}
