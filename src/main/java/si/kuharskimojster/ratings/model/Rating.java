package si.kuharskimojster.ratings.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Rating {

    private String userId;
    private RatingEnum ratingEnum;
    private String recipeId;

    public Rating(String userId, RatingEnum ratingEnum, String recipeId) {
        this.userId = userId;
        this.ratingEnum = ratingEnum;
        this.recipeId = recipeId;
    }

    public RatingEnum getRatingEnum() {
        return ratingEnum;
    }

    public void setRatingEnum(RatingEnum ratingEnum) {
        this.ratingEnum = ratingEnum;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static String toJson(Rating rating) {
        // Creating Object of ObjectMapper define in Jakson Api
        ObjectMapper Obj = new ObjectMapper();

        String jsonStr = null;
        try {
            jsonStr = Obj.writeValueAsString(rating);
            // Displaying JSON String
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }
}
