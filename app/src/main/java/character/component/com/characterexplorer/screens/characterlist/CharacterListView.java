package character.component.com.characterexplorer.screens.characterlist;

import java.util.List;

import character.component.com.characterexplorer.screens.common.controllerbase.BaseObserver;
import character.component.com.characterexplorer.model.Results;

public interface CharacterListView extends BaseObserver<CharacterListView.Listener> {
    interface Listener {
        void onCharacterClicked(String characterId);
    }

    void bindView(List<Results> rowsModels);

    void showProgressIndication();

    void hideProgressIndication();
}
