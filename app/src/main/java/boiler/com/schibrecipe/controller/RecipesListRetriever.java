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

import java.util.ArrayList;
import java.util.List;

import boiler.com.schibrecipe.model.Recipe;

import static com.android.volley.Request.Method.*;
import static com.android.volley.Response.Listener;

// RecipesListRetriever retrieves the list of recipes
public class RecipesListRetriever {

    private static final String WS_URL = "http://food2fork.com/api/search?key=b549c4c96152e677eb90de4604ca61a2";
    private static final String PAGE = "&page=";
    private static final String SEARCH = "&q=";
    private final ModelReadyNotifier mModelReadyNotifier;
    private final Context mContext;

    public RecipesListRetriever(ModelReadyNotifier modelReadyNotifier, Context context) {
        mModelReadyNotifier = modelReadyNotifier;
        mContext = context;
    }

    public void getRecipes(String search, int page) {
        StringBuilder urlBuilder = new StringBuilder(WS_URL);
        urlBuilder.append(SEARCH).append(search).append(PAGE).append(page);
        JsonObjectRequest request = new JsonObjectRequest(GET, urlBuilder.toString(), null, new Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    List<Recipe> recipes = new ArrayList<>();
                    JSONArray children = response.getJSONArray("recipes");

                    for (int i = 0; i < children.length(); ++i) {
                        JSONObject recipe = children.getJSONObject(i);
                        final String title = recipe.getString("title");
                        final String thumbnail = recipe.getString("image_url");
                        final String id = recipe.getString("recipe_id");
                        Recipe item = new Recipe(title, thumbnail, id);
                        recipes.add(item);
                    }
                    mModelReadyNotifier.modelReady(recipes);
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue queue = Volley.newRequestQueue(mContext);
        queue.add(request);
    }

    public interface ModelReadyNotifier {
        void modelReady(List<Recipe> recipes);
    }
}
