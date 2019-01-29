package character.component.com.characterexplorer.characterlist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import character.component.com.characterexplorer.R;
import character.component.com.characterexplorer.adapter.CharacterAdapter;
import character.component.com.characterexplorer.common.BaseViewMvc;
import character.component.com.characterexplorer.common.ToolbarView;
import character.component.com.characterexplorer.common.ViewMvcFactory;
import character.component.com.characterexplorer.model.Results;

public class CharacterListViewImpl extends BaseViewMvc<CharacterListView.Listener> implements CharacterListView {

    private RecyclerView mRecyclerView;
    private CharacterAdapter mCharacterAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressBar mProgressBar;
    private final ToolbarView mToolbarViewMvc;
    private final Toolbar mToolbar;


    public CharacterListViewImpl(LayoutInflater inflater, ViewGroup parent, ViewMvcFactory viewMvcFactory) {
        setRootView(inflater.inflate(R.layout.list_character, parent, false));
        mRecyclerView = findViewById(R.id.recycler_list);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mCharacterAdapter = new CharacterAdapter(getRootView().getContext(), new CharacterAdapter.OnCharaterClickListener() {
            @Override
            public void onQuestionClicked(String characterId) {
                for (Listener listener : getListeners()) {
                    listener.onCharacterClicked(characterId);
                }
            }
        });

        mRecyclerView.setAdapter(mCharacterAdapter);

        mProgressBar = findViewById(R.id.progress);

        mToolbar = findViewById(R.id.toolbar);
        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(inflater, mToolbar);
        mToolbarViewMvc.setTitle(getString(R.string.character_title));
        mToolbar.addView(mToolbarViewMvc.getRootView());
    }

    @Override
    public void bindView(List<Results> rowsModels) {
        mCharacterAdapter.bindView(rowsModels);
    }

    @Override
    public void showProgressIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        mProgressBar.setVisibility(View.GONE);
    }
}
