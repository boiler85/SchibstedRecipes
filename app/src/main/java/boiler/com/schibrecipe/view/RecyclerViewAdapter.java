package boiler.com.schibrecipe.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import boiler.com.schibrecipe.R;
import boiler.com.schibrecipe.model.Recipe;

// Adapter for the list (RecyclerView)
public class RecyclerViewAdapter extends RecyclerView.Adapter<RowHolder> {

    private final static int BUFFER = 5;
    private final List<Recipe> mRecipesList;
    private final Context mContext;
    private final AdapterListener mAdapterListener;

    public RecyclerViewAdapter(Context ctx, List<Recipe> list, AdapterListener listener) {
        mRecipesList = list;
        mContext = ctx;
        mAdapterListener = listener;
    }

    @Override
    public RowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        return new RowHolder(v);
    }

    @Override
    public void onBindViewHolder(final RowHolder holder, final int position) {
        final Recipe listItem = mRecipesList.get(position);
        holder.getLayoutPosition();
        holder.setTitle(listItem.getTitle());
        holder.setThumbnail(listItem.getImageUrl(), mContext);
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapterListener.itemSelected(listItem.getId());
            }
        });
        if (position == getItemCount() - BUFFER) {
            mAdapterListener.endOfScrollDetected();
        }
    }

    @Override
    public int getItemCount() {
        return mRecipesList == null ? 0 : mRecipesList.size();
    }

    public void clear() {
        mRecipesList.clear();
    }

    public void updateList(List<Recipe> recipes) {
        mRecipesList.addAll(recipes);
        notifyDataSetChanged();
    }

    public interface AdapterListener {
        void endOfScrollDetected();
        void itemSelected(String id);
    }
}
