package character.component.com.characterexplorer;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import character.component.com.characterexplorer.characterlist.CharacterListFragment;

public class MainActivity extends AppCompatActivity {
    
    public static File cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (getSupportFragmentManager().findFragmentByTag("CharacterDetailsFragment") == null) {
            ft.replace(R.id.frag_character, new CharacterListFragment(), "CharacterListFragment").commit();
        }
    }
}
