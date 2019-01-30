package character.component.com.characterexplorer.common.dependencyinjection;

import android.support.annotation.UiThread;

import character.component.com.characterexplorer.screens.common.network.CharacterInterceptor;
import character.component.com.characterexplorer.usecase.FetchCharacterDetailsUseCase;
import character.component.com.characterexplorer.usecase.FetchCharacterListUseCase;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static character.component.com.characterexplorer.screens.common.network.AppConstants.BASE_URL;

public class CompositionRoot {
    private Retrofit mRetrofit;
    CharacterInterceptor mCharacterInterceptor;

    @UiThread
    public Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    @UiThread
    public CharacterInterceptor getCharacterInterceptor() {
        if (mCharacterInterceptor == null) {
            mCharacterInterceptor = getRetrofit().create(CharacterInterceptor.class);
        }
        return mCharacterInterceptor;
    }

    @UiThread
    public FetchCharacterListUseCase getFetchCharacterListUseCase() {
        return new FetchCharacterListUseCase(getCharacterInterceptor());
    }

    @UiThread
    public FetchCharacterDetailsUseCase getFetchCharacterDetailsUseCase() {
        return new FetchCharacterDetailsUseCase(getCharacterInterceptor());
    }
}
