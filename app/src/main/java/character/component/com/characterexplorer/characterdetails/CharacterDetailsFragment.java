package character.component.com.characterexplorer.characterdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import character.component.com.characterexplorer.model.Results;
import character.component.com.characterexplorer.usecase.FetchCharacterDetailsUseCase;

public class CharacterDetailsFragment extends Fragment implements FetchCharacterDetailsUseCase.Listener, CharacterDetailsMvc.Listener {

    private CharacterDetailsMvc mCharacterDetailsMvc;
    private FetchCharacterDetailsUseCase mFetchCharacterDetailsUseCase;

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
        mCharacterDetailsMvc = mViewMvcFactory.getCharacterDetailsViewMvc(LayoutInflater.from(getActivity()), null);
        mFetchCharacterDetailsUseCase = new FetchCharacterDetailsUseCase();
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
    }

    @Override
    public void onNavigateUpClicked() {
        //to do back key press handling here
    }
}
