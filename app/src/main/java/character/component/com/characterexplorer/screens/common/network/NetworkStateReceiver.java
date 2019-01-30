package character.component.com.characterexplorer.screens.common.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import character.component.com.characterexplorer.screens.common.controllerbase.BaseObservable;

public class NetworkStateReceiver extends BaseObservable<NetworkStateReceiver.Listener> {

    public interface Listener {
        void onNetworkAvailable();

        void onNetworkUnAvailable();
    }

    @Override
    public View getRootView() {
        return null;
    }


    public void connect(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = manager != null ? manager.getActiveNetworkInfo() : null;

        if (ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
            notifyConnected();
        } else {
            notifyDisConnected();
        }
    }

    private void notifyConnected() {
        for (Listener listener : getListeners())
            listener.onNetworkAvailable();
    }

    private void notifyDisConnected() {
        for (Listener listener : getListeners())
            listener.onNetworkUnAvailable();
    }

}
