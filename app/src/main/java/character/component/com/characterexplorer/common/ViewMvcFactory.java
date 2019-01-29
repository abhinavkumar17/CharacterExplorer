package character.component.com.characterexplorer.common;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import character.component.com.characterexplorer.characterdetails.CharacterDetailsMvc;
import character.component.com.characterexplorer.characterdetails.CharacterDetailsMvcImpl;
import character.component.com.characterexplorer.characterlist.CharacterListView;
import character.component.com.characterexplorer.characterlist.CharacterListViewImpl;

public class ViewMvcFactory {

    private final LayoutInflater mLayoutInflater;

    public ViewMvcFactory(LayoutInflater layoutInflater) {
        mLayoutInflater = layoutInflater;
    }

    public ToolbarView getToolbarViewMvc(LayoutInflater mLayoutInflater, @Nullable ViewGroup parent) {
        return new ToolbarView(mLayoutInflater, parent);
    }

    public <T extends ViewMvc> T newInstance(Class<T> mvcViewClass, @Nullable ViewGroup container) {

        ViewMvc viewMvc;

        if (mvcViewClass == CharacterListView.class) {
            viewMvc = new CharacterListViewImpl(mLayoutInflater, container, this);
        } else if (mvcViewClass == CharacterDetailsMvc.class) {
            viewMvc = new CharacterDetailsMvcImpl(mLayoutInflater, container, this);
        } else {
            throw new IllegalArgumentException("unsupported view class " + mvcViewClass);
        }

        //noinspection unchecked
        return (T) viewMvc;
    }
}
