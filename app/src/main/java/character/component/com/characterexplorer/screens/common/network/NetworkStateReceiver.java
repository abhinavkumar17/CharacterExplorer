package character.component.com.characterexplorer.screens.common.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import character.component.com.characterexplorer.screens.common.controllerbase.BaseObservable;

public class NetworkStateReceiver extends BaseObservable<NetworkStateReceiver.Listener> {

    public interface Listener {
        void networkAvailable();

        void networkUnavailable();
    }

    @Override
    public View getRootView() {
        return null;
    }


    public void connect(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = manager.getActiveNetworkInfo();

        if (ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
            notifyConnected();
        } else {
            notifyDisConnected();
        }
    }

    private void notifyConnected() {
        for (Listener listener : getListeners())
            listener.networkAvailable();
    }

    private void notifyDisConnected() {
        for (Listener listener : getListeners())
            listener.networkUnavailable();
    }

}
