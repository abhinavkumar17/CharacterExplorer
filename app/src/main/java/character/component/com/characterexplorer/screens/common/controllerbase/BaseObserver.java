package character.component.com.characterexplorer.screens.common.controllerbase;

public interface BaseObserver<ListerType> extends ViewMvc {

    void registerListener(ListerType listener);

    void unRegisterListener(ListerType listener);
}
