package boiler.com.schibrecipe;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import boiler.com.schibrecipe.view.RecipeDetailsFragment;
import boiler.com.schibrecipe.view.RecipesListFragment;

public class MainActivity extends AppCompatActivity implements RecipesListFragment.RecipesListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.list_fragment, new RecipesListFragment(this));
        ft.commit();
    }

    @Override
    public void recipeSelected(String recipeId) {
        //TODO
    }
}
