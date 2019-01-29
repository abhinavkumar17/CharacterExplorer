package character.component.com.characterexplorer.common;

import android.app.Application;

import character.component.com.characterexplorer.dependencyinjection.CompositionRoot;

public class CharacterApplication extends Application {

    CompositionRoot mCompositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        mCompositionRoot = new CompositionRoot();
    }

    public CompositionRoot getCompositionRoot() {
        return mCompositionRoot;
    }
}
