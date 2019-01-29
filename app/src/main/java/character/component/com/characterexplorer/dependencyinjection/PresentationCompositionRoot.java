package character.component.com.characterexplorer.dependencyinjection;

import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import character.component.com.characterexplorer.common.ViewMvcFactory;
import character.component.com.characterexplorer.common.dialog.DialogsManager;
import character.component.com.characterexplorer.usecase.FetchCharacterDetailsUseCase;
import character.component.com.characterexplorer.usecase.FetchCharacterListUseCase;

public class PresentationCompositionRoot {

    private final CompositionRoot mCompositionRoot;
    private FragmentManager mFragmentManager;
    private LayoutInflater mLayoutInflater;

    public PresentationCompositionRoot(CompositionRoot compositionRoot,
                                       FragmentManager fragmentManager,
                                       LayoutInflater layoutInflater) {
        mCompositionRoot = compositionRoot;
        mFragmentManager = fragmentManager;
        mLayoutInflater = layoutInflater;
    }

    public DialogsManager getDialogsManager() {
        return new DialogsManager(mFragmentManager);
    }

    public FetchCharacterListUseCase getFetchCharacterListUseCase() {
        return mCompositionRoot.getFetchCharacterListUseCase();
    }

    public FetchCharacterDetailsUseCase getFetchCharacterDetailsUseCase() {
        return mCompositionRoot.getFetchCharacterDetailsUseCase();
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(mLayoutInflater);
    }
}
