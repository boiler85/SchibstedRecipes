package boiler.com.schibrecipe.model;

// Model representation of a single Recipe
public class Recipe {

    protected final String mTitle;
    protected final String mImageUrl;
    protected final String mId;

    public Recipe(String title, String thumbnail, String id) {
        mTitle = title;
        mImageUrl = thumbnail;
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getId() {
        return mId;
    }
}
