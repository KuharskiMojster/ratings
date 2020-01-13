package si.kuharskimojster.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import si.kuharskimojster.api.model.ResponseModel;
import si.kuharskimojster.models.Rating;
import si.kuharskimojster.models.RatingEnum;
import si.kuharskimojster.services.RecipeService;

import javax.websocket.server.PathParam;


@RestController
@RequestMapping("/v1/rating")
public class RatingsController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private RecipeService recipeService;

    @Value(value = "${kafka.ratings.topic}")
    private String kafkaTopic;


    private void sendMessage(String msg) {
        kafkaTemplate.send(kafkaTopic, msg);
    }

    @PostMapping
    public ResponseEntity<ResponseModel> postRating(@RequestParam(name = "userId") Long userId, @RequestParam(name = "rating") RatingEnum ratingEnum, @RequestParam(name = "recipeId") Long recipeId) {
        Rating rating = new Rating(userId, ratingEnum.getRating(), recipeId);
        String ratingJson = Rating.toJson(rating);
        sendMessage(ratingJson);
        return new ResponseEntity<>(new ResponseModel("User with id: " + userId + " gave a rating " + ratingEnum + " to the recipe with the given id: " + recipeId, HttpStatus.OK.value()), HttpStatus.OK);
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<ResponseModel> getRecipeRating(@PathVariable("recipeId") Long recipeId) {
        return new ResponseEntity<>(new ResponseModel(recipeService.getRecipeAvgRatings(recipeId), HttpStatus.OK.value()), HttpStatus.OK);
    }



}
