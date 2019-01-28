package character.component.com.characterexplorer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import character.component.com.characterexplorer.characterlist.CharacterListFragment;

public class MainActivity extends AppCompatActivity {

    private CharacterListFragment characterFragment;
    public static File cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.frag_character) != null) {
            // Check if the fragment has already been created. If present, reuse the same instance
            characterFragment = (CharacterListFragment) getSupportFragmentManager().findFragmentByTag("CharacterListFragment");
            if (characterFragment == null) {
                getSupportFragmentManager().beginTransaction().add(R.id.frag_character, new CharacterListFragment(), "AboutCountryFragment").commit();
            }
        }

        cache = getCacheDir();
    }
}
