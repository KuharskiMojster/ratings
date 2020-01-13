package si.kuharskimojster.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import si.kuharskimojster.api.model.ResponseModel;
import si.kuharskimojster.models.Comment;
import si.kuharskimojster.services.RecipeService;


@RestController
@RequestMapping("/v1")
public class CommentController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${kafka.comments.topic}")
    private String kafkaCommentsTopic;

    private void sendMessage(String msg) {
        kafkaTemplate.send(kafkaCommentsTopic, msg);
    }

    @PostMapping(path = "/comment")
    public ResponseEntity<ResponseModel> postComment(@RequestParam(name = "userId") Long userId, @RequestParam(name = "comment") String comment, @RequestParam(name = "recipeId") Long recipeId) {
        Comment newComment = new Comment(userId, comment, recipeId);
        String jsonComment = Comment.toJson(newComment);
        sendMessage(jsonComment);
        return new ResponseEntity<>(new ResponseModel("User with id: " + userId + " gave a comment [" + comment + "] to the recipe with the given id: " + recipeId, HttpStatus.OK.value()), HttpStatus.OK);
    }

    @GetMapping(path = "/comments")
    public ResponseEntity<ResponseModel> getRecipeComments(@RequestParam(name = "recipeId") Long recipeId, @RequestParam(name = "limit") int limit) {
        return new ResponseEntity<>(new ResponseModel(recipeService.getRecipeComments(recipeId, limit), HttpStatus.OK.value()), HttpStatus.OK);
    }





}