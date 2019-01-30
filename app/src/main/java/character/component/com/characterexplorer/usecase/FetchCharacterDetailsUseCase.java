package character.component.com.characterexplorer.usecase;

import java.util.ArrayList;
import java.util.List;

import character.component.com.characterexplorer.screens.common.controllerbase.BaseViewMvc;
import character.component.com.characterexplorer.screens.common.network.CharacterInterceptor;
import character.component.com.characterexplorer.model.CharactersResponse;
import character.component.com.characterexplorer.model.Results;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static character.component.com.characterexplorer.screens.common.network.AppConstants.HASH_KEY;
import static character.component.com.characterexplorer.screens.common.network.AppConstants.LIMIT;
import static character.component.com.characterexplorer.screens.common.network.AppConstants.PUBLIC_KEY;
import static character.component.com.characterexplorer.screens.common.network.AppConstants.TIME_STAMP;

public class FetchCharacterDetailsUseCase extends BaseViewMvc<FetchCharacterDetailsUseCase.Listener> {

    public interface Listener {
        void characterDetailsFetchSuccess(Results results);

        void characterDetailsFetchFailed();
    }

    Call<CharactersResponse> responseCall;
    CharacterInterceptor mCharacterDetalisInterceptor;
    List<Listener> mListener = new ArrayList<>();

    public FetchCharacterDetailsUseCase(CharacterInterceptor mCharacterDetalisInterceptor) {
        this.mCharacterDetalisInterceptor = mCharacterDetalisInterceptor;
    }

    public void FetchCharacterDetais(String characterId) {
        responseCall = mCharacterDetalisInterceptor.getCharacterDetails(characterId, PUBLIC_KEY, HASH_KEY, LIMIT, TIME_STAMP);
        responseCall.enqueue(new Callback<CharactersResponse>() {
            @Override
            public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                notifySuccess(response.body().getData().getResults().get(0));
            }

            @Override
            public void onFailure(Call<CharactersResponse> call, Throwable t) {
                notifyFailure();
            }
        });
    }

    private void notifySuccess(Results results) {
        for (Listener listener : mListener) {
            listener.characterDetailsFetchSuccess(results);
        }
    }

    private void notifyFailure() {
        for (Listener listener : mListener) {
            listener.characterDetailsFetchFailed();
        }
    }

    public void registerListener(Listener listener) {
        mListener.add(listener);
    }

    public void unRegisterListener(Listener listener) {
        mListener.remove(listener);
    }
}
