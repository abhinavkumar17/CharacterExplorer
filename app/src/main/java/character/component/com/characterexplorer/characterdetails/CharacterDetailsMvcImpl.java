package character.component.com.characterexplorer.characterdetails;

import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import character.component.com.characterexplorer.R;
import character.component.com.characterexplorer.ViewUtils;
import character.component.com.characterexplorer.common.BaseViewMvc;
import character.component.com.characterexplorer.common.ToolbarView;
import character.component.com.characterexplorer.common.ViewMvcFactory;
import character.component.com.characterexplorer.model.Results;

public class CharacterDetailsMvcImpl extends BaseViewMvc<CharacterDetailsMvc.Listener> implements CharacterDetailsMvc {
    private ImageView mImageView;
    private TextView mTextTitle;
    private TextView mTextDesc;
    private ProgressBar mProgressBar;
    private Button mButton;
    private final ToolbarView mToolbarViewMvc;
    private final Toolbar mToolbar;


    public CharacterDetailsMvcImpl(LayoutInflater inflater, ViewGroup parent, ViewMvcFactory viewMvcFactory) {
        setRootView(inflater.inflate(R.layout.details_character, parent, false));
        mTextTitle = findViewById(R.id.name_character);
        mImageView = findViewById(R.id.img_character);
        mTextDesc = findViewById(R.id.character_desc);
        mProgressBar = findViewById(R.id.progress);
        mButton = findViewById(R.id.buttonPanel);

        mToolbar = findViewById(R.id.toolbar);
        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(inflater, mToolbar);
        mToolbar.addView(mToolbarViewMvc.getRootView());
        mToolbarViewMvc.enableUpButtonAndListen(new ToolbarView.Listener() {
            @Override
            public void onNavigateUpClicked() {
                for (Listener listener : getListeners()) {
                    listener.onNavigateUpClicked();
                }
            }
        });
    }

    @Override
    public void bindView(Results rowsModels) {
        ViewUtils.hasValidText(getContext(), rowsModels.getName(), mTextTitle);
        ViewUtils.hasValidText(getContext(), rowsModels.getDescription(), mTextDesc);
        mToolbarViewMvc.setTitle(rowsModels.getName());
        Glide.with(getContext())
                .load(rowsModels.getThumbnail().getPath() + "." + rowsModels.getThumbnail().getExtension())
                .apply(new RequestOptions().override(200))
                .into(mImageView);

        final String url = rowsModels.getUrls().get(0).getUrl();
        if (!TextUtils.isEmpty(url)) {
            mButton.setVisibility(View.VISIBLE);
            mButton.setText(getString(R.string.character_info));
            final Uri myUri = Uri.parse(url);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewUtils.redirectToExternalLink(getRootView().getContext(), myUri);
                }
            });
        } else {
            mButton.setVisibility(View.INVISIBLE);
        }
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
