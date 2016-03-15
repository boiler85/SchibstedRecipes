package boiler.com.schibrecipe.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import boiler.com.schibrecipe.R;
import boiler.com.schibrecipe.controller.RecipeDetailsRetriever;
import boiler.com.schibrecipe.model.FullRecipe;
import boiler.com.schibrecipe.utils.ImageLoaderSingleton;

// Fragment for single recipe view
public class RecipeDetailsFragment extends Fragment implements RecipeDetailsRetriever.RecipeNotifier {

    private TextView mTitleTV;
    private NetworkImageView mPhoto;
    private ListView mIngredientsLV;
    private Button mViewInstructionsBtn;
    private Button mViewOriginalBtn;
    private TextView mPublisherTV;
    private TextView mSocialRankTV;
    private String mRecipeId;

    public RecipeDetailsFragment() {
        super();
    }

    public void setRecipeId(String recipeId) {
        mRecipeId = recipeId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        mTitleTV = (TextView) view.findViewById(R.id.recipe_title);
        mPhoto = (NetworkImageView) view.findViewById(R.id.photo);
        mIngredientsLV = (ListView) view.findViewById(R.id.ingredients_list);
        mViewInstructionsBtn = (Button) view.findViewById(R.id.instructions_btn);
        mViewOriginalBtn = (Button) view.findViewById(R.id.original_btn);
        mPublisherTV = (TextView) view.findViewById(R.id.publisher_txt);
        mSocialRankTV = (TextView) view.findViewById(R.id.social_rank_txt);
        if (mRecipeId != null) {
            applyRecipe(mRecipeId);
        }
        return view;
    }

    public void applyRecipe(String recipeId) {
        new RecipeDetailsRetriever(this, getActivity(), recipeId);
    }

    @Override
    public void recipeReady(final FullRecipe recipe) {
        mTitleTV.setText(recipe.getTitle());
        ImageLoader imageLoader = ImageLoaderSingleton.getInstance(getActivity()).getImageLoader();
        mPhoto.setImageUrl(recipe.getImageUrl(), imageLoader);
        mPublisherTV.setText(recipe.getPublisher());
        StringBuilder socialRank = new StringBuilder(getString(R.string.social_rank));
        socialRank.append(": ").append(recipe.getSocialRank());
        mSocialRankTV.setText(socialRank.toString());
        mViewOriginalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl(recipe.getOriginalUrl());
            }
        });
        mViewInstructionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl(recipe.getInstructionsUrl());
            }
        });
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, recipe.getIngredients());
        mIngredientsLV.setAdapter(adapter);
    }

    private void openUrl(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}

