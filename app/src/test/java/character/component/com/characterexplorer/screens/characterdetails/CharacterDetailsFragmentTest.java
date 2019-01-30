package character.component.com.characterexplorer.screens.characterdetails;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import character.component.com.characterexplorer.MainActivity;
import character.component.com.characterexplorer.R;
import character.component.com.characterexplorer.model.Results;
import character.component.com.characterexplorer.model.Thumbnail;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(RobolectricTestRunner.class)
public class CharacterDetailsFragmentTest {

    private static final String TEST_CHARACTER_NAME = "Test Name";
    private static final String TEST_CHARACTER_DESCRIPTION = "Test Description";

    private MainActivity activity;
    private CharacterDetailsFragment fragment;
    Results results;

    @Before
    public void setUp() {
        // setup activity
        activity = Robolectric.setupActivity(MainActivity.class);
        assertNotNull(activity);

        // setup fragment
        fragment = CharacterDetailsFragment.newInstance("");
        startFragment(fragment);
        assertNotNull(fragment);
        results = new Results(
                "",
                TEST_CHARACTER_NAME,
                TEST_CHARACTER_DESCRIPTION,
                new Thumbnail(),
                "",
                "2014-04-29T14:18:17-0400",
                null,
                null,
                null,
                null,
                null
        );
    }

    @Test
    public void show_character_name_and_description_when_response_is_valid() {
        fragment.mCharacterDetailsMvc.bindView(results);

        TextView title = fragment.mCharacterDetailsMvc.getRootView().findViewById(R.id.name_character);
        assertEquals(title.getText().toString(), (TEST_CHARACTER_NAME));

        TextView desc = fragment.mCharacterDetailsMvc.getRootView().findViewById(R.id.character_desc);
        assertEquals(desc.getText().toString(), (TEST_CHARACTER_DESCRIPTION));
    }

    @Test
    public void do_not_show_more_info_button_if_url_is_null_or_not_valid() {
        fragment.mCharacterDetailsMvc.bindView(results);
        Button moreInfo = fragment.mCharacterDetailsMvc.getRootView().findViewById(R.id.buttonPanel);
        assertNull(moreInfo.getText().toString(), null);
        assertViewIsNotVisible(fragment.mCharacterDetailsMvc.getRootView().findViewById(R.id.buttonPanel));
    }

    @Test
    public void tool_bar_name_should_be_character_name() {
        fragment.mCharacterDetailsMvc.bindView(results);
        Toolbar toolbarTop = (Toolbar) fragment.mCharacterDetailsMvc.getRootView().findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbarTop.findViewById(R.id.txt_toolbar_title);
        assertEquals(mTitle.getText().toString(), TEST_CHARACTER_NAME);

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

    public static void assertViewIsVisible(View view) {
        assertNotNull(view);
        assertThat(view.getVisibility(), equalTo(View.VISIBLE));
    }

    public static void assertViewIsNotVisible(View view) {
        assertNotNull(view);
        assertThat(view.getVisibility(), not(equalTo(View.VISIBLE)));
    }
}