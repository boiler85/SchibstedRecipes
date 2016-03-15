package boiler.com.schibrecipe.controller;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import boiler.com.schibrecipe.model.FullRecipe;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Response.Listener;

// RecipeDetailsRetriever retrieves details about single recipe
public class RecipeDetailsRetriever {

    private static final String WS_URL = "http://food2fork.com/api/get?key=b549c4c96152e677eb90de4604ca61a2";
    private static final String ID = "&rId=";
    private final RecipeNotifier mRecipeNotifier;

    public RecipeDetailsRetriever(RecipeNotifier modelReadyNotifier, Context context, String recipeId) {
        mRecipeNotifier = modelReadyNotifier;
        StringBuilder urlBuilder = new StringBuilder(WS_URL);
        urlBuilder.append(ID).append(recipeId);
        JsonObjectRequest request = new JsonObjectRequest(GET, urlBuilder.toString(), null, new Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject recipe = response.getJSONObject("recipe");
                    final String title = recipe.getString("title");
                    final String thumbnail = recipe.getString("image_url");
                    final String id = recipe.getString("recipe_id");
                    final String publisher = recipe.getString("publisher");
                    final String originalUrl = recipe.getString("source_url");
                    final String instructionsUrl = recipe.getString("f2f_url");
                    final double socialRank = recipe.getDouble("social_rank");
                    FullRecipe item = new FullRecipe(title, thumbnail, id, publisher, originalUrl, instructionsUrl, socialRank);
                    JSONArray ingredients = recipe.getJSONArray("ingredients");
                    for (int i = 0; i < ingredients.length(); ++i) {
                        item.addIngredient(ingredients.getString(i));
                    }
                    mRecipeNotifier.recipeReady(item);
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public interface RecipeNotifier {
        void recipeReady(FullRecipe recipe);
    }
}
