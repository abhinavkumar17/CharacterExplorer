package character.component.com.characterexplorer.common.application;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import character.component.com.characterexplorer.screens.common.controllerbase.ViewMvcFactory;
import character.component.com.characterexplorer.screens.common.dialog.DialogsManager;
import character.component.com.characterexplorer.usecase.FetchCharacterDetailsUseCase;
import character.component.com.characterexplorer.usecase.FetchCharacterListUseCase;
import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    private final ApplicationComponent mApplicationComponent;
    private final FragmentActivity mActivity;

    public PresentationModule(FragmentActivity activity, ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
        mActivity = activity;
    }

    @Provides
    Activity getActivity() {
        return mActivity;
    }

    @Provides
    LayoutInflater getLayoutInflater() {
        return LayoutInflater.from(mActivity);
    }

    @Provides
    FragmentManager getFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }

    @Provides
    DialogsManager getDialogsManager(FragmentManager fragmentManager) {
        return new DialogsManager(fragmentManager);
    }

    @Provides
    FetchCharacterListUseCase getFetchCharacterListUseCase() {
        return mApplicationComponent.getFetchCharacterListUseCase();
    }

    @Provides
    FetchCharacterDetailsUseCase getFetchCharacterDetailsUseCase() {
        return mApplicationComponent.getFetchCharacterDetailsUseCase();
    }

    @Provides
    ViewMvcFactory getViewMvcFactory(LayoutInflater inflater) {
        return new ViewMvcFactory(inflater);
    }
}
