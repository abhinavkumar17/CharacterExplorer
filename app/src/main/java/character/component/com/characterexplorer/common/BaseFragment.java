package character.component.com.characterexplorer.common;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import character.component.com.characterexplorer.application.ApplicationComponent;
import character.component.com.characterexplorer.application.DaggerPresentationComponent;
import character.component.com.characterexplorer.application.PresentationComponent;
import character.component.com.characterexplorer.application.PresentationModule;
import character.component.com.characterexplorer.dependencyinjection.PresentationCompositionRoot;

public class BaseFragment extends Fragment {

    PresentationCompositionRoot mPresentationCompositionRoot;

    @UiThread
    protected PresentationCompositionRoot getCompositionRoot() {
        if (mPresentationCompositionRoot == null) {
            mPresentationCompositionRoot = new PresentationCompositionRoot(
                    getApplicationComponent(),
                    getFragmentManager(),
                    LayoutInflater.from(getActivity())
            );
        }

        return mPresentationCompositionRoot;
    }

    protected PresentationComponent getPresentationComponent() {
        return DaggerPresentationComponent.builder()
                .presentationModule(new PresentationModule(getActivity(), getApplicationComponent()))
                .build();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((CharacterApplication) getAttachedActivity().getApplication()).getApplicationComponent();
    }

    @NonNull
    private Activity getAttachedActivity() {
        return requireActivity();
    }
}
