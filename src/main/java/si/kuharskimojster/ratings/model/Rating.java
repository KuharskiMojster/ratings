package si.kuharskimojster.ratings.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Rating {

    private Long userId;
    private RatingEnum ratingEnum;
    private Long recipeId;

    public Rating(Long userId, RatingEnum ratingEnum, Long recipeId) {
        this.userId = userId;
        this.ratingEnum = ratingEnum;
        this.recipeId = recipeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public RatingEnum getRatingEnum() {
        return ratingEnum;
    }

    public void setRatingEnum(RatingEnum ratingEnum) {
        this.ratingEnum = ratingEnum;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
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
