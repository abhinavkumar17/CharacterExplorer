package character.component.com.characterexplorer.application;

import character.component.com.characterexplorer.characterdetails.CharacterDetailsFragment;
import character.component.com.characterexplorer.characterlist.CharacterListFragment;
import dagger.Component;

@Component(modules = PresentationModule.class)
public interface PresentationComponent {

    void inject(CharacterListFragment characterListFragment);

    void inject(CharacterDetailsFragment characterDetailsFragment);
}
