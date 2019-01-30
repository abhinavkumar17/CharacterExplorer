package character.component.com.characterexplorer.screens.characterdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import character.component.com.characterexplorer.screens.common.controllerbase.BaseFragment;
import character.component.com.characterexplorer.screens.common.controllerbase.ViewMvcFactory;
import character.component.com.characterexplorer.screens.common.dialog.DialogsManager;
import character.component.com.characterexplorer.screens.common.dialog.ServerErrorDialogFragment;
import character.component.com.characterexplorer.model.Results;
import character.component.com.characterexplorer.usecase.FetchCharacterDetailsUseCase;

public class CharacterDetailsFragment extends BaseFragment implements FetchCharacterDetailsUseCase.Listener, CharacterDetailsMvc.Listener {

    CharacterDetailsMvc mCharacterDetailsMvc;
    @Inject
    FetchCharacterDetailsUseCase mFetchCharacterDetailsUseCase;
    @Inject
    DialogsManager mDialogsManager;
    @Inject
    ViewMvcFactory mViewMvcFactory;

    private static final String ARG_QUESTION_ID = "ARG_QUESTION_ID";

    public static CharacterDetailsFragment newInstance(String characterId) {
        Bundle args = new Bundle();
        args.putString(ARG_QUESTION_ID, characterId);
        CharacterDetailsFragment fragment = new CharacterDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private String getCharacterId() {
        return getArguments().getString(ARG_QUESTION_ID);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        getPresentationComponent().inject(this);
        mCharacterDetailsMvc = mViewMvcFactory.newInstance(CharacterDetailsMvc.class, null);
        return mCharacterDetailsMvc.getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
        mFetchCharacterDetailsUseCase.registerListener(this);
        mCharacterDetailsMvc.registerListener(this);
        mCharacterDetailsMvc.showProgressIndication();
        mFetchCharacterDetailsUseCase.FetchCharacterDetais(getCharacterId());
    }

    @Override
    public void onStop() {
        super.onStop();
        mFetchCharacterDetailsUseCase.unRegisterListener(this);
        mCharacterDetailsMvc.unRegisterListener(this);
    }

    @Override
    public void characterDetailsFetchSuccess(Results results) {
        mCharacterDetailsMvc.hideProgressIndication();
        mCharacterDetailsMvc.bindView(results);
    }

    @Override
    public void characterDetailsFetchFailed() {
        mCharacterDetailsMvc.hideProgressIndication();
        mDialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(), "");
    }

    @Override
    public void onNavigateUpClicked() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }
    }
}
