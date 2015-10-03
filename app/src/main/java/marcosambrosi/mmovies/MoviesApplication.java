package marcosambrosi.mmovies;

import android.app.Application;
import android.app.Service;
import android.content.pm.ApplicationInfo;

import marcosambrosi.mmovies.model.Configuration;
import marcosambrosi.mmovies.network.ServiceController;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by marcosambrosi on 10/3/15.
 */
public class MoviesApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        checkIfCallConfigurationServiceNeeded();
    }

    private void checkIfCallConfigurationServiceNeeded() {
        ServiceController.getInstance().configuration(new Callback<Configuration>() {
            @Override
            public void success(Configuration configuration, Response response) {
                if (configuration != null) {

                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (error != null) {
                    
                }
            }
        });
    }
}
