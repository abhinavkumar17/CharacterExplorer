package character.component.com.characterexplorer.screens.common.controllerbase;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import character.component.com.characterexplorer.CharacterApplication;
import character.component.com.characterexplorer.common.application.ApplicationComponent;
import character.component.com.characterexplorer.common.application.DaggerPresentationComponent;
import character.component.com.characterexplorer.common.application.PresentationComponent;
import character.component.com.characterexplorer.common.application.PresentationModule;
import character.component.com.characterexplorer.common.dependencyinjection.PresentationCompositionRoot;

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
