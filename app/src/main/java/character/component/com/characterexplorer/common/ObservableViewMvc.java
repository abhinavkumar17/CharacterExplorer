package character.component.com.characterexplorer.common;

public interface ObservableViewMvc<ListenerType> extends ViewMvc {

    /**
     * Register a listener that will be notified of any input events performed on this MVC view
     */
    void registerListener(ListenerType listener);

    /**
     * Unregister a previously registered listener. Does nothing if the listener wasn't registered.
     */
    void unRegisterListener(ListenerType listener);

}
