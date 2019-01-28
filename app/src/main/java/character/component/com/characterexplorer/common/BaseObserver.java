package character.component.com.characterexplorer.common;

public interface BaseObserver<ListerType> extends ViewMvc {

    void registerListener(ListerType listener);

    void unRegisterListener(ListerType listener);
}
