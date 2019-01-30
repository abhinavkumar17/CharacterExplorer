package character.component.com.characterexplorer.application;


import javax.inject.Singleton;

import character.component.com.characterexplorer.common.CharacterInterceptor;
import character.component.com.characterexplorer.usecase.FetchCharacterDetailsUseCase;
import character.component.com.characterexplorer.usecase.FetchCharacterListUseCase;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static character.component.com.characterexplorer.common.AppConstants.BASE_URL;

@Module
public class ApplicationModule {

    @Singleton
    @Provides
    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public CharacterInterceptor getCharacterInterceptor(Retrofit retrofit) {
        return retrofit.create(CharacterInterceptor.class);
    }

    @Provides
    public FetchCharacterListUseCase getFetchCharacterListUseCase() {
        return new FetchCharacterListUseCase(getCharacterInterceptor(getRetrofit()));
    }

    @Provides
    public FetchCharacterDetailsUseCase getFetchCharacterDetailsUseCase() {
        return new FetchCharacterDetailsUseCase(getCharacterInterceptor(getRetrofit()));
    }
}
