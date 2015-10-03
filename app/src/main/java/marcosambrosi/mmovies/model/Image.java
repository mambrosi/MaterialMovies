package marcosambrosi.mmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by marcosambrosi on 10/3/15.
 */
public class Image {

    @SerializedName("base_url")
    String baseUrl;

    @SerializedName("secure_base_url")
    String secureBaseUrl;

    @SerializedName("backdrop_sizes")
    List<String> backdropSizes;

    @SerializedName("logo_sizes")
    List<String> logoSizes;

    @SerializedName("poster_sizes")
    List<String> posterSizes;

    @SerializedName("profile_sizes")
    List<String> profileSizes;

    @SerializedName("still_sizes")
    List<String> stillSizes;

}
