package boiler.com.schibrecipe.model;

// Model representation of a single Recipe
public class Recipe {

    private final String mTitle;
    private final String mImageUrl;
    private final String mId;

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
