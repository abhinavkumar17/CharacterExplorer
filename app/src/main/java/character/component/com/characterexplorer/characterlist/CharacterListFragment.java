package character.component.com.characterexplorer.characterlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import character.component.com.characterexplorer.R;
import character.component.com.characterexplorer.characterdetails.CharacterDetailsFragment;
import character.component.com.characterexplorer.model.Results;
import character.component.com.characterexplorer.usecase.FetchCharacterListUseCase;


public class CharacterListFragment extends Fragment implements CharacterListView.Listener {
    private CharacterListView mCharacterListView;
    private FetchCharacterListUseCase mFetchCharacterListUseCase;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCharacterListView = mViewMvcFactory.getCharacterListViewMvc(LayoutInflater.from(getActivity()), null);
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
    }

    @Override
    public void onCharacterClicked(String characterId) {
        CharacterDetailsFragment characterDetailsFragment = CharacterDetailsFragment.newInstance(characterId);
        //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_character, characterDetailsFragment, "CharacterDetailsFragment").commit();
        getFragmentManager().beginTransaction().replace(R.id.frag_character, characterDetailsFragment).addToBackStack("CharacterDetailsFragment").commit();
    }

    @Override
    public void networkAvailable() {
        FetchCharacterList();
    }

    @Override
    public void networkUnavailable() {
        mCharacterListView.hideProgressIndication();
    }
}
