package boiler.com.schibrecipe.model;

import java.util.ArrayList;

// Model representation of a single Full Recipe
public class FullRecipe extends Recipe {

    protected final String mPublisher;
    protected final String mOriginalUrl;
    protected final String mInstructionsUrl;
    protected final double mSocialRank;
    protected final ArrayList<String> mIngredients;

    public FullRecipe(String title, String thumbnail, String id,
                      String publisher, String originalUrl, String instructionsUrl, double socialRank) {
        super(title, thumbnail, id);
        mPublisher = publisher;
        mOriginalUrl = originalUrl;
        mInstructionsUrl = instructionsUrl;
        mSocialRank = socialRank;
        mIngredients = new ArrayList<>();
    }

    public void addIngredient(String ingredient) {
        mIngredients.add(ingredient);
    }

    public String getPublisher() {
        return mPublisher;
    }

    public String getOriginalUrl() {
        return mOriginalUrl;
    }

    public String getInstructionsUrl() {
        return mInstructionsUrl;
    }

    public double getSocialRank() {
        return mSocialRank;
    }

    public ArrayList<String> getIngredients() {
        return mIngredients;
    }
}
