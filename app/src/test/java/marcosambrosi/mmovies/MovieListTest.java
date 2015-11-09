package marcosambrosi.mmovies;

import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;

import marcosambrosi.mmovies.activity.MainActivity;

import static org.junit.Assert.assertNotNull;

/**
 * Created by marcosambrosi on 11/6/15.
 */

@RunWith(RobolectricGradleTestRunner.class)
public class MovieListTest {

    MainActivity mMainActivity;

    @Before
    public void setUp() throws Exception {
        mMainActivity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void test_activity_exists() {
        assertNotNull(null);
    }

    @Test
    public void test_recycler_view_exists() {
        RecyclerView recyclerView = (RecyclerView) mMainActivity.
                findViewById(R.id.recycler_view_movies);
        assertNotNull(recyclerView);

    }


}
