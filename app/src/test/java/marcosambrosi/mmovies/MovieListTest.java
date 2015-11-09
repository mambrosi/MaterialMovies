package marcosambrosi.mmovies;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import marcosambrosi.mmovies.activity.MainActivity;

import static org.junit.Assert.assertNotNull;

/**
 * Created by marcosambrosi on 11/6/15.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MovieListTest {

    MainActivity mMainActivity;

    @Before
    public void setUp() throws Exception {
        mMainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void testActivityExists() {
        assertNotNull(mMainActivity);
    }


}
