package marcosambrosi.movieit.network;

import marcosambrosi.movieit.model.MovieResponse;
import marcosambrosi.movieit.util.Constants;
import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by marcosambrosi on 5/6/15.
 */
public class ServiceController {

    private static ServiceController sInstance;
    private static MovieItService sService;

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


        sService = restAdapter.build().create(MovieItService.class);
    }

    public static ServiceController getInstance() {
        if (sInstance == null) {
            sInstance = new ServiceController();
        }
        return sInstance;
    }

    public void discoverMovies(int page, Callback<MovieResponse> callback){
        sService.discoverMovie(page, callback);
    }
}
