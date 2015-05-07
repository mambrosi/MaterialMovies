package marcosambrosi.movieit.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marcosambrosi on 5/6/15.
 */
public class Movie {

//            "adult": false,
//            "backdrop_path": "/cKw3HY835PMp6bzse3LMivIY5Nl.jpg",
//            "id": 1884,
//            "original_title": "The Ewok Adventure",
//            "release_date": "1984-11-25",
//            "poster_path": "/x2nKP0FCJwNLHgCyUI1cL8bF6nL.jpg",
//            "popularity": 0.72905031478,
//            "title": "The Ewok Adventure",
//            "vote_average": 10,
//            "vote_count": 4

    public String title;

    @SerializedName("poster_path")
    public String posterUrl;

    @SerializedName("vote_average")
    public int averageVotes;
}
