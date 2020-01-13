package si.kuharskimojster.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Rating {

    private Long userId;
    private int rating;
    private Long recipeId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Rating(Long userId, int rating, Long recipeId) {
        this.userId = userId;
        this.rating = rating;
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
