package character.component.com.characterexplorer.screens.characterdetails;

import character.component.com.characterexplorer.screens.common.controllerbase.BaseObserver;
import character.component.com.characterexplorer.model.Results;

public interface CharacterDetailsMvc extends BaseObserver<CharacterDetailsMvc.Listener> {
    interface Listener {
        void onNavigateUpClicked();
    }

    void bindView(Results rowsModels);

    void showProgressIndication();

    void hideProgressIndication();
}
