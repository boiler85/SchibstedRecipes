package boiler.com.schibrecipe;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import boiler.com.schibrecipe.view.RecipeDetailsFragment;

public class RecipeActivity extends AppCompatActivity {

    public static final String RECIPE_ID_KEY = "recipe_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.dual_pane)) {
            finish();
            return;
        }
        setContentView(R.layout.activity_recipe);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        RecipeDetailsFragment fragment = (RecipeDetailsFragment) fm.findFragmentById(R.id.details_fragment);
        if (fragment == null) {
            fragment = new RecipeDetailsFragment();
            ft.add(R.id.details_fragment, fragment);
            ft.commit();
        }
        fragment.setRecipeId(getIntent().getStringExtra(RECIPE_ID_KEY));
    }
}
