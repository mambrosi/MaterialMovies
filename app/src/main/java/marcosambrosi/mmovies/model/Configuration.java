package marcosambrosi.mmovies.model;

import com.google.gson.Gson;
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


    public String toJsonString() {
        return new Gson().toJson(this,
                Configuration.class);
    }

    public static Configuration fromJsonString(String jsonString) {
        return new Gson().fromJson(jsonString, Configuration.class);
    }

}
