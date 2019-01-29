package character.component.com.characterexplorer.characterdetails;

import character.component.com.characterexplorer.model.Results;

public interface CharacterDetailsMvc {
    interface Listener {
        void onNavigateUpClicked();
    }

    void bindView(Results rowsModels);

    void showProgressIndication();

    void hideProgressIndication();
}
