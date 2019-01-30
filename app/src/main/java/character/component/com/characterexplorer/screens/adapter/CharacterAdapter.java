package character.component.com.characterexplorer.screens.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import character.component.com.characterexplorer.R;
import character.component.com.characterexplorer.common.ViewUtils;
import character.component.com.characterexplorer.model.Results;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.MyViewHolder> {


    public interface OnCharaterClickListener {
        void onQuestionClicked(String characterId);
    }

    Context context;
    private List<Results> rowsModels = new ArrayList<>();
    private OnCharaterClickListener mOnCharaterClickListener;


    public CharacterAdapter(Context context, OnCharaterClickListener mOnCharaterClickListener) {
        this.context = context;
        this.mOnCharaterClickListener = mOnCharaterClickListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextTitle;
        public ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.name_character);
            mImageView = itemView.findViewById(R.id.img_character);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int position) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_character, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        ViewUtils.hasValidText(context, rowsModels.get(position).getName(), holder.mTextTitle);
        Glide.with(context.getApplicationContext())
                .load(rowsModels.get(position).getThumbnail().getPath() + "." + rowsModels.get(position).getThumbnail().getExtension())
                .apply(new RequestOptions().override(300))
                .into(holder.mImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnCharaterClickListener.onQuestionClicked(rowsModels.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return rowsModels.size();
    }

    public void bindView(List<Results> rowsModels) {
        this.rowsModels = rowsModels;
        notifyDataSetChanged();
    }

}
