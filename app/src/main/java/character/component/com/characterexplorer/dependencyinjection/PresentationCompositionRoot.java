package character.component.com.characterexplorer.dependencyinjection;

import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import character.component.com.characterexplorer.application.ApplicationComponent;
import character.component.com.characterexplorer.common.ViewMvcFactory;
import character.component.com.characterexplorer.common.dialog.DialogsManager;
import character.component.com.characterexplorer.usecase.FetchCharacterDetailsUseCase;
import character.component.com.characterexplorer.usecase.FetchCharacterListUseCase;

public class PresentationCompositionRoot {

    private final ApplicationComponent mApplicationComponent;
    private FragmentManager mFragmentManager;
    private LayoutInflater mLayoutInflater;

    public PresentationCompositionRoot(ApplicationComponent applicationComponent,
                                       FragmentManager fragmentManager,
                                       LayoutInflater layoutInflater) {
        mApplicationComponent = applicationComponent;
        mFragmentManager = fragmentManager;
        mLayoutInflater = layoutInflater;
    }

    public DialogsManager getDialogsManager() {
        return new DialogsManager(mFragmentManager);
    }

    public FetchCharacterListUseCase getFetchCharacterListUseCase() {
        return mApplicationComponent.getFetchCharacterListUseCase();
    }

    public FetchCharacterDetailsUseCase getFetchCharacterDetailsUseCase() {
        return mApplicationComponent.getFetchCharacterDetailsUseCase();
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(mLayoutInflater);
    }
}
