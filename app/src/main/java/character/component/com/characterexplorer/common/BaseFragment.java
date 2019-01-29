package character.component.com.characterexplorer.common;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import character.component.com.characterexplorer.dependencyinjection.CompositionRoot;
import character.component.com.characterexplorer.dependencyinjection.PresentationCompositionRoot;

public class BaseFragment extends Fragment {

    PresentationCompositionRoot mPresentationCompositionRoot;

    @UiThread
    protected PresentationCompositionRoot getCompositionRoot() {
        if (mPresentationCompositionRoot == null) {
            mPresentationCompositionRoot = new PresentationCompositionRoot(
                    getAppCompositionRoot(),
                    getFragmentManager(),
                    LayoutInflater.from(getActivity())
            );
        }

        return mPresentationCompositionRoot;
    }

    protected CompositionRoot getAppCompositionRoot() {
        return ((CharacterApplication) getAttachedActivity().getApplication()).getCompositionRoot();
    }

    @NonNull
    private Activity getAttachedActivity() {
        return requireActivity();
    }
}
