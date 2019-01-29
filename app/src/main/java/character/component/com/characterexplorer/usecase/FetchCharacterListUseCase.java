package character.component.com.characterexplorer.usecase;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import character.component.com.characterexplorer.RetrofitClientInstance;
import character.component.com.characterexplorer.common.BaseViewMvc;
import character.component.com.characterexplorer.common.CharacterInterceptor;
import character.component.com.characterexplorer.model.CharactersResponse;
import character.component.com.characterexplorer.model.Results;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static character.component.com.characterexplorer.common.AppConstants.HASH_KEY;
import static character.component.com.characterexplorer.common.AppConstants.LIMIT;
import static character.component.com.characterexplorer.common.AppConstants.PUBLIC_KEY;
import static character.component.com.characterexplorer.common.AppConstants.TIME_STAMP;

public class FetchCharacterListUseCase extends BaseViewMvc<FetchCharacterListUseCase.Listener> {

    public interface Listener {

        void onFetchSuccess(List<Results> rowsModels);

        void onFetchFail();
    }

    CharacterInterceptor mCharacterInterceptor;
    Call<CharactersResponse> responseCall;

    List<Listener> mListener = new ArrayList<>();

    public FetchCharacterListUseCase() {
        mCharacterInterceptor = RetrofitClientInstance.getRetrofitInstance().create(CharacterInterceptor.class);
    }

    @Override
    public View getRootView() {
        return null;
    }


    public void registerListener(Listener listener) {
        mListener.add(listener);
    }


    public void unRegisterListener(Listener listener) {
        mListener.remove(listener);
    }

    public void fetchCharacterList() {
        //String hash = HashGenerator.generate(TIME_STAMP, PRIVATE_KEY, PUBLIC_KEY);
        responseCall = mCharacterInterceptor.getCharacters(PUBLIC_KEY, HASH_KEY, LIMIT, TIME_STAMP);
        responseCall.enqueue(new Callback<CharactersResponse>() {
            @Override
            public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                List<Results> rowsModels = response.body().getData().getResults();
                onUseCaseFetchSuccess(rowsModels);
            }

            @Override
            public void onFailure(Call<CharactersResponse> call, Throwable t) {
                onUseCaseFetchFailure();
            }
        });
    }

    private void onUseCaseFetchFailure() {
        for (Listener listener : mListener) {
            listener.onFetchFail();
        }
    }

    private void onUseCaseFetchSuccess(List<Results> rowsModels) {
        for (Listener listener : mListener) {
            listener.onFetchSuccess(rowsModels);
        }
    }
}
