package character.component.com.characterexplorer.screens.characterlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import character.component.com.characterexplorer.R;
import character.component.com.characterexplorer.screens.characterdetails.CharacterDetailsFragment;
import character.component.com.characterexplorer.screens.common.controllerbase.BaseFragment;
import character.component.com.characterexplorer.screens.common.network.NetworkStateReceiver;
import character.component.com.characterexplorer.screens.common.controllerbase.ViewMvcFactory;
import character.component.com.characterexplorer.screens.common.dialog.DialogsManager;
import character.component.com.characterexplorer.screens.common.dialog.ServerErrorDialogFragment;
import character.component.com.characterexplorer.model.Results;
import character.component.com.characterexplorer.usecase.FetchCharacterListUseCase;


public class CharacterListFragment extends BaseFragment implements CharacterListView.Listener, FetchCharacterListUseCase.Listener,
        NetworkStateReceiver.Listener {


    CharacterListView mCharacterListView;
    @Inject
    FetchCharacterListUseCase mFetchCharacterListUseCase;
    @Inject
    DialogsManager mDialogsManager;
    @Inject
    ViewMvcFactory mViewMvcFactory;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getPresentationComponent().inject(this);
        mCharacterListView = mViewMvcFactory.newInstance(CharacterListView.class, null);
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
            getFragmentManager().beginTransaction().replace(R.id.frag_character, characterDetailsFragment, "CharacterDetailsFragment").addToBackStack("CharacterDetailsFragment").commit();
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
