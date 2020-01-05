package si.kuharskimojster.ratings.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import si.kuharskimojster.ratings.model.Rating;
import si.kuharskimojster.ratings.model.RatingEnum;
import si.kuharskimojster.ratings.model.ResponseModel;

@RestController
@RequestMapping("/v1/rating")
public class RatingsController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${kafka.ratings.topic}")
    private String kafkaTopic;


    private void sendMessage(String msg) {
        kafkaTemplate.send(kafkaTopic, msg);
    }

    @PostMapping
    public ResponseEntity<ResponseModel> postRating(@RequestParam(name = "userId") String userId, @RequestParam(name = "rating") RatingEnum ratingEnum, @RequestParam(name = "recipeId") String recipeId) {
        Rating rating = new Rating(userId, ratingEnum, recipeId);
        String ratingJson = Rating.toJson(rating);
        sendMessage(ratingJson);
        return new ResponseEntity<>(new ResponseModel("User with id: " + userId + " gave a rating " + ratingEnum + " to the recipe with the given id: " + recipeId, HttpStatus.OK.value()), HttpStatus.OK);
    }


}
