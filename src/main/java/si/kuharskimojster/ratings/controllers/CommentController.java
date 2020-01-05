package si.kuharskimojster.ratings.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import si.kuharskimojster.ratings.model.Comment;
import si.kuharskimojster.ratings.model.ResponseModel;

@RestController
@RequestMapping("/v1")
public class CommentController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${kafka.comments.topic}")
    private String kafkaCommentsTopic;

    @GetMapping("/health")
    public ResponseEntity<ResponseModel> getHealth() {
        return new ResponseEntity<>(new ResponseModel("App is running...", HttpStatus.OK.value()), HttpStatus.OK);
    }

    private void sendMessage(String msg) {
        kafkaTemplate.send(kafkaCommentsTopic, msg);
    }

    @PostMapping(path = "/comment")
    public ResponseEntity<ResponseModel> postRating(@RequestParam(name = "userId") String userId, @RequestParam(name = "comment") String comment, @RequestParam(name = "recipeId") String recipeId) {
        Comment newComennt = new Comment(userId, comment, recipeId);
        String jsonComment = Comment.toJson(newComennt);
        sendMessage(jsonComment);
        return new ResponseEntity<>(new ResponseModel("User with id: " + userId + " gave a comment [" + comment + "] to the recipe with the given id: " + recipeId, HttpStatus.OK.value()), HttpStatus.OK);
    }


}