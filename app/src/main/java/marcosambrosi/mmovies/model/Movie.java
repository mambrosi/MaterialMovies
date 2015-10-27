/*
 *
 *
 *  *
 *  *  * Copyright (C)  2015 Marcos Ambrosi marcosambrosi@gmail.com
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *  *
 *
 */

package marcosambrosi.mmovies.model;

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

    public String overview;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("backdrop_path")
    public String backdropPath;


    @SerializedName("vote_average")
    public double averageVotes;
}
