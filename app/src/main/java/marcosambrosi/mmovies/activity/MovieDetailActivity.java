package marcosambrosi.mmovies.activity;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private FloatingActionButton mAddToWatchlistButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Movie movie = Movie.fromJsonString(getIntent().getStringExtra(Constants.EXTRA.MOVIE));

        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mCollapsingToolbarLayout.
                setTitle(movie.title);

        RecyclerView recyclerViewMovies = (RecyclerView) findViewById(R.id.recycler_view_reviews);

        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMovies.setHasFixedSize(true);

        final ReviewsAdapter adapter = new ReviewsAdapter();

        recyclerViewMovies.setAdapter(adapter);


        final ImageView movieImage = (ImageView) findViewById(R.id.movie_image);

        String backdropUrl = MoviesApplication.getInstance().getConfiguration().image.baseUrl
                .concat("w1280")
                .concat(movie.backdropPath);

        Picasso.with(this)
                .load(backdropUrl)
                .into(movieImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        applyPalette(movieImage);
                    }

                    @Override
                    public void onError() {

                    }
                });



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


        mAddToWatchlistButton = (FloatingActionButton) findViewById(R.id.button_fave);
        mAddToWatchlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToWatchlist();
            }
        });


    }

    private void addToWatchlist() {
        Snackbar.make(findViewById(R.id.coordinator_layout),
                R.string.movie_added_to_watchlist,
                Snackbar.LENGTH_LONG)
                .setAction(R.string.snackbar_action_undo,
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Implement my undo logic here
                            }
                        })
                .show();
    }

    private void applyPalette(final ImageView source) {
        final Bitmap bitmap = ((BitmapDrawable) source.
                getDrawable()).
                getBitmap();

        new Palette.Builder(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrantSwatch = palette.getDarkVibrantSwatch();

                if (vibrantSwatch != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(vibrantSwatch.getRgb());
                    }

                    mAddToWatchlistButton.
                            setBackgroundTintList(ColorStateList.valueOf(vibrantSwatch.getRgb()));

                    mAddToWatchlistButton.setRippleColor(vibrantSwatch.getRgb());

                    final Drawable upArrow = ContextCompat.getDrawable(MovieDetailActivity.this,
                            R.drawable.abc_ic_ab_back_mtrl_am_alpha);
                    upArrow.setColorFilter(vibrantSwatch.getTitleTextColor(),
                            PorterDuff.Mode.SRC_ATOP);

                    getSupportActionBar().setHomeAsUpIndicator(upArrow);
                }
            }
        });


    }

}
