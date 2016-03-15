package boiler.com.schibrecipe.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import boiler.com.schibrecipe.R;
import boiler.com.schibrecipe.utils.ImageLoaderSingleton;

// View representation of a single row
public class RowHolder extends RecyclerView.ViewHolder {

    private final NetworkImageView mThumbnail;
    private final TextView mTitle;
    final RelativeLayout mLayout;

    public RowHolder(View view) {
        super(view);
        mThumbnail = (NetworkImageView) view.findViewById(R.id.thumbnail);
        mTitle = (TextView) view.findViewById(R.id.recipe_title);
        mLayout = (RelativeLayout) view.findViewById(R.id.row_layout);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setThumbnail(String url, Context context) {
        ImageLoader imageLoader = ImageLoaderSingleton.getInstance(context).getImageLoader();
        mThumbnail.setImageUrl(url, imageLoader);
    }
}
