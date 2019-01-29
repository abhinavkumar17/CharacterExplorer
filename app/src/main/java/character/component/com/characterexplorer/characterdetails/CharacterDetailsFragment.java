package character.component.com.characterexplorer.characterdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import character.component.com.characterexplorer.common.BaseFragment;
import character.component.com.characterexplorer.common.dialog.DialogsManager;
import character.component.com.characterexplorer.common.dialog.ServerErrorDialogFragment;
import character.component.com.characterexplorer.model.Results;
import character.component.com.characterexplorer.usecase.FetchCharacterDetailsUseCase;

public class CharacterDetailsFragment extends BaseFragment implements FetchCharacterDetailsUseCase.Listener, CharacterDetailsMvc.Listener {

    private CharacterDetailsMvc mCharacterDetailsMvc;
    private FetchCharacterDetailsUseCase mFetchCharacterDetailsUseCase;
    private DialogsManager mDialogsManager;
    Bundle args;

    private static final String ARG_QUESTION_ID = "ARG_QUESTION_ID";

    public static CharacterDetailsFragment newInstance(String characterId) {
        Bundle args = new Bundle();
        args.putString(ARG_QUESTION_ID, characterId);
        CharacterDetailsFragment fragment = new CharacterDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private String getCharacterId() {
        return args.getString(ARG_QUESTION_ID);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        mCharacterDetailsMvc = getCompositionRoot().getViewMvcFactory().newInstance(CharacterDetailsMvc.class, null);
        mFetchCharacterDetailsUseCase = getCompositionRoot().getFetchCharacterDetailsUseCase();
        mDialogsManager = getCompositionRoot().getDialogsManager();
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
