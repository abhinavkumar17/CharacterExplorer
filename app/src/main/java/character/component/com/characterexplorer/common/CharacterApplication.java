package character.component.com.characterexplorer.common;

import android.app.Application;

import character.component.com.characterexplorer.application.ApplicationComponent;
import character.component.com.characterexplorer.application.ApplicationModule;
import character.component.com.characterexplorer.application.DaggerApplicationComponent;

public class CharacterApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
