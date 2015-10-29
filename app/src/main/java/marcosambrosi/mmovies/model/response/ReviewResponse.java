package marcosambrosi.mmovies.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import marcosambrosi.mmovies.model.Review;

/**
 * Created by marcosambrosi on 10/28/15.
 */
public class ReviewResponse {

    @SerializedName("results")
    public List<Review> reviews;
}
