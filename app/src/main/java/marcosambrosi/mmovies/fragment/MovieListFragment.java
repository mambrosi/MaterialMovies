package marcosambrosi.mmovies.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import marcosambrosi.mmovies.R;
import marcosambrosi.mmovies.activity.MovieDetailActivity;
import marcosambrosi.mmovies.adapter.MoviesAdapter;
import marcosambrosi.mmovies.model.Movie;
import marcosambrosi.mmovies.model.response.MovieResponse;
import marcosambrosi.mmovies.network.ServiceController;
import marcosambrosi.mmovies.util.Constants;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MovieListFragment extends Fragment implements MoviesAdapter.OnMovieClickedListener {


    @StringDef({
            TYPE_DISCOVER,
            TYPE_LATEST
    })

    @Retention(RetentionPolicy.SOURCE)
    public @interface ListType {
    }

    public static final String TYPE_DISCOVER = "discover";
    public static final String TYPE_LATEST = "latest";


    private String mListType;

    public MovieListFragment() {
        // Required empty public constructor
    }

    public static MovieListFragment newInstance(@ListType String listType) {
        MovieListFragment fragment = new MovieListFragment();
        fragment.mListType = listType;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerViewMovies = (RecyclerView) view.findViewById(R.id.recycler_view_movies);

        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewMovies.setHasFixedSize(true);

        final MoviesAdapter adapter = new MoviesAdapter();

        adapter.setMovieClickedListener(this);

        recyclerViewMovies.setAdapter(adapter);

        Callback<MovieResponse> callback = new Callback<MovieResponse>() {
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
        };

        if (TYPE_DISCOVER.equals(mListType)) {
            ServiceController.getInstance().discoverMovies(callback);
        } else if (TYPE_LATEST.equals(mListType)) {
            ServiceController.getInstance().nowPlaying(callback);
        }


    }

    @Override
    public void onMovieClicked(Movie movie, ImageView movieImage) {
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);


        intent.putExtra(Constants.EXTRA.MOVIE, movie.toJsonString());

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), (View) movieImage, "movie_image");
        getActivity().startActivity(intent, options.toBundle());
    }
}
