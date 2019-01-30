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

public class FetchCharacterDetailsUseCase extends BaseViewMvc<FetchCharacterDetailsUseCase.Listener> {

    public interface Listener {
        void onFetchOfCharacterDetailsSucceeded(Results results);

        void onFetchOfCharacterDetailsFailed();
    }

    Call<CharactersResponse> responseCall;
    CharacterInterceptor mCharacterDetalisInterceptor;
    List<Listener> mListener = new ArrayList<>();

    public FetchCharacterDetailsUseCase(CharacterInterceptor mCharacterDetalisInterceptor) {
        this.mCharacterDetalisInterceptor = mCharacterDetalisInterceptor;
    }

    public void fetchCharacterDetailsAndNotify(String characterId) {
        responseCall = mCharacterDetalisInterceptor.getCharacterDetails(characterId, PUBLIC_KEY, HASH_KEY, LIMIT, TIME_STAMP);
        responseCall.enqueue(new Callback<CharactersResponse>() {
            @Override
            public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                if (response.isSuccessful() && response.body().getData() != null
                        && response.body().getData().getResults() != null
                        && response.body().getData().getResults().get(0) != null) {
                    onCharacterDetailsFetchSuccess(response.body().getData().getResults().get(0));
                } else {
                    onCharacterDetailsFetchFailure();
                }
            }

            @Override
            public void onFailure(Call<CharactersResponse> call, Throwable t) {
                onCharacterDetailsFetchFailure();
            }
        });
    }

    private void onCharacterDetailsFetchSuccess(Results results) {
        for (Listener listener : mListener) {
            listener.onFetchOfCharacterDetailsSucceeded(results);
        }
    }

    private void onCharacterDetailsFetchFailure() {
        for (Listener listener : mListener) {
            listener.onFetchOfCharacterDetailsFailed();
        }
    }

    public void registerListener(Listener listener) {
        mListener.add(listener);
    }

    public void unRegisterListener(Listener listener) {
        mListener.remove(listener);
    }
}
