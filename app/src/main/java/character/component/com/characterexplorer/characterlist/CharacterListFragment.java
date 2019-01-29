package character.component.com.characterexplorer.characterlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import character.component.com.characterexplorer.R;
import character.component.com.characterexplorer.characterdetails.CharacterDetailsFragment;
import character.component.com.characterexplorer.common.BaseFragment;
import character.component.com.characterexplorer.common.NetworkStateReceiver;
import character.component.com.characterexplorer.common.dialog.DialogsManager;
import character.component.com.characterexplorer.common.dialog.ServerErrorDialogFragment;
import character.component.com.characterexplorer.model.Results;
import character.component.com.characterexplorer.usecase.FetchCharacterListUseCase;


public class CharacterListFragment extends BaseFragment implements CharacterListView.Listener, FetchCharacterListUseCase.Listener,
        NetworkStateReceiver.Listener {
    private CharacterListView mCharacterListView;
    private FetchCharacterListUseCase mFetchCharacterListUseCase;
    private DialogsManager mDialogsManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCharacterListView = getCompositionRoot().getViewMvcFactory().newInstance(CharacterListView.class, null);
        mFetchCharacterListUseCase = getCompositionRoot().getFetchCharacterListUseCase();
        mDialogsManager = getCompositionRoot().getDialogsManager();
        return mCharacterListView.getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
        FetchCharacterList();
    }

    private void FetchCharacterList() {
        mCharacterListView.registerListener(this);
        mFetchCharacterListUseCase.registerListener(this);
        mCharacterListView.showProgressIndication();
        mFetchCharacterListUseCase.fetchCharacterList();
    }

    @Override
    public void onStop() {
        super.onStop();
        mCharacterListView.unRegisterListener(this);
        mFetchCharacterListUseCase.unRegisterListener(this);
    }

    @Override
    public void onFetchSuccess(List<Results> rowsModels) {
        mCharacterListView.hideProgressIndication();
        mCharacterListView.bindView(rowsModels);
    }

    @Override
    public void onFetchFail() {
        mCharacterListView.hideProgressIndication();
        mDialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(), "");
    }

    @Override
    public void onCharacterClicked(String characterId) {
        CharacterDetailsFragment characterDetailsFragment = CharacterDetailsFragment.newInstance(characterId);
        if (getFragmentManager() != null)
            getFragmentManager().beginTransaction().replace(R.id.frag_character, characterDetailsFragment).addToBackStack("CharacterDetailsFragment").commit();
    }

    @Override
    public void networkAvailable() {
        FetchCharacterList();
    }

    @Override
    public void networkUnavailable() {
        mCharacterListView.hideProgressIndication();
        mDialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(), "");
    }
}
