package character.component.com.characterexplorer.screens.characterlist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import character.component.com.characterexplorer.MainActivity;
import character.component.com.characterexplorer.R;
import character.component.com.characterexplorer.model.Results;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class CharacterListFragmentTest {

    private MainActivity activity;
    private CharacterListFragment fragment;
    List<Results> results;

    @Before
    public void setUp() {
        // setup activity
        activity = Robolectric.setupActivity(MainActivity.class);
        assertNotNull(activity);

        // setup fragment
        fragment = new CharacterListFragment();
        startFragment(fragment);
        assertNotNull(fragment);
        results = null;
    }

    @Test
    public void tool_bar_name_should_be_characters() {
        Toolbar toolbarTop = fragment.mCharacterListView.getRootView().findViewById(R.id.toolbar);
        TextView mTitle = toolbarTop.findViewById(R.id.txt_toolbar_title);
        assertEquals(mTitle.getText().toString(), "CHARACTERS");
    }


    public static void startFragment(Fragment fragment) {
        FragmentActivity activity = Robolectric.buildActivity(FragmentActivity.class)
                .create()
                .start()
                .resume()
                .get();

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment, null);
        fragmentTransaction.commit();
    }
}