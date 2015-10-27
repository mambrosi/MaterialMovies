package marcosambrosi.mmovies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import marcosambrosi.mmovies.MoviesApplication;
import marcosambrosi.mmovies.R;
import marcosambrosi.mmovies.model.Movie;

/**
 * Created by marcosambrosi on 10/26/15.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    List<Movie> mMovies = new ArrayList<>();
    OnMovieClickedListener mMovieClickedListener;

    public interface OnMovieClickedListener {
        void onMovieClicked(Movie movie, ImageView movieImage);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.movie_list_item, parent, false);


        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {

        final Movie movie = mMovies.get(position);



        holder.movieTitle.setText(movie.title);


        holder.movieOverview.setText(movie.overview);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMovieClickedListener != null) {
                    mMovieClickedListener.onMovieClicked(movie, holder.movieImage);
                }
            }
        });
        String posterUrl = MoviesApplication.getInstance().getConfiguration().image.baseUrl
                .concat("w1280")
                .concat(movie.backdropPath);

        Picasso.with(holder.itemView.getContext())
                .load(posterUrl)
                .placeholder(R.color.darker_color)
                .error(R.color.darker_color)
                .into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public void addAll(List<Movie> movies) {
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public void setMovieClickedListener(OnMovieClickedListener movieClickedListener) {
        this.mMovieClickedListener = movieClickedListener;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle;
        ImageView movieImage;
        TextView movieOverview;


        public MovieViewHolder(View itemView) {
            super(itemView);

            movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
            movieImage = (ImageView) itemView.findViewById(R.id.movie_image);
            movieOverview = (TextView) itemView.findViewById(R.id.movie_overview);

        }
    }
}
