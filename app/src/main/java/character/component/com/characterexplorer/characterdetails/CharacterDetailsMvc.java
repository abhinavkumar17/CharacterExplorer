package character.component.com.characterexplorer.characterdetails;

import character.component.com.characterexplorer.common.BaseObserver;
import character.component.com.characterexplorer.model.Results;

public interface CharacterDetailsMvc extends BaseObserver<CharacterDetailsMvc.Listener> {
    interface Listener {
        void onNavigateUpClicked();
    }

    void bindView(Results rowsModels);

    void showProgressIndication();

    void hideProgressIndication();
}
