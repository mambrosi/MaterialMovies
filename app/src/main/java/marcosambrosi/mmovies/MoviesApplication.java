package marcosambrosi.mmovies;

import android.app.Application;
import android.content.SharedPreferences;
import android.text.TextUtils;

import marcosambrosi.mmovies.model.Configuration;
import marcosambrosi.mmovies.util.Constants;

/**
 * Created by marcosambrosi on 10/3/15.
 */
public class MoviesApplication extends Application {


    private static MoviesApplication sInstance;
    private SharedPreferences mSharedPreferences;
    private Configuration mConfiguration;


    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        mSharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);

    }

    public void addPreference(String key, String value) {
        if (mSharedPreferences != null) {
            mSharedPreferences.edit().putString(key, value).apply();
        }
    }

    public boolean hasConfiguration() {
        return TextUtils.isEmpty(getPreference(Constants.PREFERENCES.CONFIG));
    }

    public String getPreference(String key) {
        if (mSharedPreferences != null) {
            return mSharedPreferences.getString(key, "");
        }

        return "";
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        sInstance = null;
        mSharedPreferences = null;
    }

    public static MoviesApplication getInstance() {
        return sInstance;
    }

    public Configuration getConfiguration() {
        return mConfiguration;
    }

    public void setConfiguration(Configuration configuration) {
        this.mConfiguration = mConfiguration;
    }
}
