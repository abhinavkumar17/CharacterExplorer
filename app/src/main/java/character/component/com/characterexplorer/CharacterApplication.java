package character.component.com.characterexplorer;

import android.app.Application;

import character.component.com.characterexplorer.common.application.ApplicationComponent;
import character.component.com.characterexplorer.common.application.ApplicationModule;
import character.component.com.characterexplorer.common.application.DaggerApplicationComponent;

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
