package character.component.com.characterexplorer.screens.common.controllerbase;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseObservable<ListenerType> implements BaseObserver<ListenerType> {

    Set<ListenerType> mListners = new HashSet<>();

    @Override
    public void registerListener(ListenerType listener) {
        mListners.add(listener);
    }

    @Override
    public void unRegisterListener(ListenerType listener) {
        mListners.remove(listener);
    }

    public Set<ListenerType> getListeners() {
        return Collections.unmodifiableSet(mListners);
    }
}
