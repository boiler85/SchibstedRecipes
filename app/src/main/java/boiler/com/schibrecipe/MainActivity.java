package boiler.com.schibrecipe;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import boiler.com.schibrecipe.view.RecipeDetailsFragment;
import boiler.com.schibrecipe.view.RecipesListFragment;

public class MainActivity extends AppCompatActivity implements RecipesListFragment.RecipesListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        RecipesListFragment fragment = (RecipesListFragment) fm.findFragmentById(R.id.list_fragment);
        if (fragment == null) {
            fragment = new RecipesListFragment();
            ft.add(R.id.list_fragment, fragment);
            ft.commit();
        }
        fragment.setRecipesListListener(this);
    }

    @Override
    public void recipeSelected(String recipeId) {
        if (getResources().getBoolean(R.bool.dual_pane)) {
            FragmentManager fm = getFragmentManager();
            RecipeDetailsFragment fragment = (RecipeDetailsFragment) fm.findFragmentById(R.id.recipe_fragment);
            fragment.applyRecipe(recipeId);
        } else {
            Intent i = new Intent(this, RecipeActivity.class);
            i.putExtra(RecipeActivity.RECIPE_ID_KEY, recipeId);
            startActivity(i);
        }
    }
}
