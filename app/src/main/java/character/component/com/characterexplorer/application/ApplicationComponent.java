package character.component.com.characterexplorer.application;

import javax.inject.Singleton;

import character.component.com.characterexplorer.usecase.FetchCharacterDetailsUseCase;
import character.component.com.characterexplorer.usecase.FetchCharacterListUseCase;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    FetchCharacterListUseCase getFetchCharacterListUseCase();

    FetchCharacterDetailsUseCase getFetchCharacterDetailsUseCase();
}
