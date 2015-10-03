package marcosambrosi.mmovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by marcosambrosi on 10/3/15.
 */
public class Configuration {


    @SerializedName("images")
    Image image;

    @SerializedName("change_keys")
    List<String> changeKeys;

}
