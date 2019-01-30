package character.component.com.characterexplorer.screens.common.toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import character.component.com.characterexplorer.R;
import character.component.com.characterexplorer.screens.common.controllerbase.BaseViewMvc;

public class ToolbarView extends BaseViewMvc<ToolbarView.Listener> {

    public interface Listener {
        void onNavigateUpClicked();
    }

    Listener mNavigateUpClickListener;

    private final TextView mTxtTitle;
    private final ImageView mImageView;

    public ToolbarView(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_toolbar, parent, false));
        mTxtTitle = findViewById(R.id.txt_toolbar_title);
        mImageView = findViewById(R.id.back);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNavigateUpClickListener.onNavigateUpClicked();
            }
        });
    }

    public void setTitle(String title) {
        mTxtTitle.setText(title);
    }

    public void enableUpButtonAndListen(Listener navigateUpClickListener) {
        mNavigateUpClickListener = navigateUpClickListener;
        mImageView.setVisibility(View.VISIBLE);
    }
}
