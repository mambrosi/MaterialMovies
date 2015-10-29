package marcosambrosi.mmovies.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import marcosambrosi.mmovies.MoviesApplication;
import marcosambrosi.mmovies.R;
import marcosambrosi.mmovies.adapter.ReviewsAdapter;
import marcosambrosi.mmovies.model.Movie;
import marcosambrosi.mmovies.model.response.ReviewResponse;
import marcosambrosi.mmovies.network.ServiceController;
import marcosambrosi.mmovies.util.Constants;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Movie movie = Movie.fromJsonString(getIntent().getStringExtra(Constants.EXTRA.MOVIE));

        getSupportActionBar().setTitle(movie.title);

        final ImageView movieImage = (ImageView) findViewById(R.id.movie_image);

        String backdropUrl = MoviesApplication.getInstance().getConfiguration().image.baseUrl
                .concat("w1280")
                .concat(movie.backdropPath);

        Picasso.with(this)
                .load(backdropUrl)
                .into(movieImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        colorToolbar(movieImage);
                    }

                    @Override
                    public void onError() {

                    }
                });

        ImageView moviePoster = (ImageView) findViewById(R.id.movie_poster);

        String posterUrl = MoviesApplication.getInstance().getConfiguration().image.baseUrl
                .concat("w342")
                .concat(movie.posterPath);

        Picasso.with(this)
                .load(posterUrl)
                .into(moviePoster);


        RecyclerView recyclerViewMovies = (RecyclerView) findViewById(R.id.recycler_view_reviews);

        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMovies.setHasFixedSize(true);

        final ReviewsAdapter adapter = new ReviewsAdapter();

        recyclerViewMovies.setAdapter(adapter);

        ServiceController.getInstance().getMovieReviews(movie.id,
                new retrofit.Callback<ReviewResponse>() {
                    @Override
                    public void success(ReviewResponse reviewResponse, Response response) {
                        adapter.addAll(reviewResponse.reviews);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });


    }

    private void colorToolbar(final ImageView source) {
        final Bitmap bitmap = ((BitmapDrawable) source.
                getDrawable()).
                getBitmap();

        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrantSwatch = palette.getDarkVibrantSwatch();

                if (vibrantSwatch != null) {
                    mToolbar.setTitleTextColor(vibrantSwatch.
                            getTitleTextColor());
                    mToolbar.setBackgroundColor(vibrantSwatch.
                            getRgb());

                    getWindow().getDecorView().setBackgroundColor(vibrantSwatch.
                            getRgb());
                }
            }
        });


    }

}
