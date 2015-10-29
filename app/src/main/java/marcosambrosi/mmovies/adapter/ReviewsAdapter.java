package marcosambrosi.mmovies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import marcosambrosi.mmovies.R;
import marcosambrosi.mmovies.model.Review;

/**
 * Created by marcosambrosi on 10/28/15.
 */
public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {


    private List<Review> mReviews = new ArrayList<>();


    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.review_item, parent, false);


        return new ReviewViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    public void addAll(List<Review> reviews) {
        mReviews.addAll(reviews);
        notifyDataSetChanged();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {

        public ReviewViewHolder(View itemView) {
            super(itemView);
        }
    }
}
