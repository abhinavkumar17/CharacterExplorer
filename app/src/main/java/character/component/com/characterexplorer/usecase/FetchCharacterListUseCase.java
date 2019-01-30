package character.component.com.characterexplorer.usecase;

import java.util.ArrayList;
import java.util.List;

import character.component.com.characterexplorer.model.CharactersResponse;
import character.component.com.characterexplorer.model.Results;
import character.component.com.characterexplorer.screens.common.controllerbase.BaseViewMvc;
import character.component.com.characterexplorer.screens.common.network.CharacterInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static character.component.com.characterexplorer.screens.common.network.AppConstants.HASH_KEY;
import static character.component.com.characterexplorer.screens.common.network.AppConstants.LIMIT;
import static character.component.com.characterexplorer.screens.common.network.AppConstants.PUBLIC_KEY;
import static character.component.com.characterexplorer.screens.common.network.AppConstants.TIME_STAMP;

public class FetchCharacterListUseCase extends BaseViewMvc<FetchCharacterListUseCase.Listener> {

    public interface Listener {

        void onFetchSuccess(List<Results> rowsModels);

        void onFetchFail();
    }

    CharacterInterceptor mCharacterInterceptor;
    Call<CharactersResponse> responseCall;

    List<Listener> mListener = new ArrayList<>();

    public FetchCharacterListUseCase(CharacterInterceptor mCharacterInterceptor) {
        this.mCharacterInterceptor = mCharacterInterceptor;
    }


    public void registerListener(Listener listener) {
        mListener.add(listener);
    }


    public void unRegisterListener(Listener listener) {
        mListener.remove(listener);
    }

    public void fetchCharacterList() {
        responseCall = mCharacterInterceptor.getCharacters(PUBLIC_KEY, HASH_KEY, LIMIT, TIME_STAMP);
        responseCall.enqueue(new Callback<CharactersResponse>() {
            @Override
            public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                List<Results> rowsModels = response.body().getData().getResults();
                if (response.isSuccessful() && response.body().getData() != null
                        && response.body().getData().getResults() != null) {
                    onUseCaseFetchSuccess(rowsModels);
                } else {
                    onUseCaseFetchFailure();
                }
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
