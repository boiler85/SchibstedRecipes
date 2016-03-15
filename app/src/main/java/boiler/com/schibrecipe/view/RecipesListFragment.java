package boiler.com.schibrecipe.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import boiler.com.schibrecipe.R;
import boiler.com.schibrecipe.controller.RecipesListRetriever;
import boiler.com.schibrecipe.model.Recipe;

// Fragment for list of recipes view
public class RecipesListFragment extends Fragment
        implements RecipesListRetriever.ModelReadyNotifier, RecyclerViewAdapter.AdapterListener {

    private RecipesListListener mRecipesListListener;
    private RecipesListRetriever mRecipesListRetriever;
    private RecyclerView mRecyclerView;
    private List<Recipe> mRecipes;
    private RecyclerViewAdapter mAdapter;
    private String mSearch;
    private int mPage;
    private final TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            mSearch = s.toString();
            mAdapter.clear();
            mPage = 0;
            mRecipesListRetriever.getRecipes(mSearch, mPage++);
        }
    };

    public RecipesListFragment() {
        super();
    }

    public void setRecipesListListener(RecipesListListener listener) {
        mRecipesListListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mRecipes = new ArrayList<>();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new RecyclerViewAdapter(getActivity(), mRecipes, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecipesListRetriever = new RecipesListRetriever(this, getActivity());
        mPage = 0;
        mSearch = "";
        mRecipesListRetriever.getRecipes(mSearch, mPage++);
        EditText searchEdit = (EditText) view.findViewById(R.id.search);
        searchEdit.addTextChangedListener(mTextWatcher);
        return view;
    }

    @Override
    public void modelReady(List<Recipe> recipes) {
        mAdapter.updateList(recipes);
    }

    @Override
    public void endOfScrollDetected() {
        mRecipesListRetriever.getRecipes(mSearch, mPage++);
    }

    @Override
    public void itemSelected(String id) {
        if (mRecipesListListener != null) mRecipesListListener.recipeSelected(id);
    }

    public interface RecipesListListener {
        void recipeSelected(String recipeId);
    }
}

