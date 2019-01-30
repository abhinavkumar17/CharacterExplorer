package character.component.com.characterexplorer.common.application;

import character.component.com.characterexplorer.screens.characterdetails.CharacterDetailsFragment;
import character.component.com.characterexplorer.screens.characterlist.CharacterListFragment;
import dagger.Component;

@Component(modules = PresentationModule.class)
public interface PresentationComponent {

    void inject(CharacterListFragment characterListFragment);

    void inject(CharacterDetailsFragment characterDetailsFragment);
}
