package marcosambrosi.mmovies;

import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import marcosambrosi.mmovies.activity.MainActivity;

/**
 * Created by marcosambrosi on 11/6/15.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MovieListTest extends ActivityInstrumentationTestCase2<MainActivity> {


    public MovieListTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void test_activity_exists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

    public void test_recycler_view_exists() {
        RecyclerView recyclerView = (RecyclerView) getActivity().
                findViewById(R.id.recycler_view_movies);

        assertNotNull(recyclerView);

    }


}
