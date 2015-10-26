package marcosambrosi.mmovies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import marcosambrosi.mmovies.model.Movie;

/**
 * Created by marcosambrosi on 10/26/15.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    List<Movie> mMovies = new ArrayList<>();

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        Movie movie = mMovies.get(position);

//        String posterUrl = Constants.CONFIG != null ? Constants.CONFIG.images.getBase_url()
//                .concat("w500")
//                .concat(movie.backDropPath) : null;
//        Picasso.with(mContext)
//                .load(posterUrl)
//                .placeholder(R.color.material_blue_grey_800)
//                .error(R.color.material_blue_grey_800)
//                .into(holder.imageViewMovie);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public void addAll(List<Movie> movies) {
        mMovies.addAll(movies);
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView movieName;
        ImageView movieImage;


        public MovieViewHolder(View itemView) {
            super(itemView);
        }
    }
}
