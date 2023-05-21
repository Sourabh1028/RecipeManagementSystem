package com.geekster.Recipe_Management_System_API.Controller;

import com.geekster.Recipe_Management_System_API.Models.Comment;
import com.geekster.Recipe_Management_System_API.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping()
    String addComment(@RequestBody Comment comment)
    {
        return commentService.addComment(comment);
    }

    @GetMapping("/{recipeName}")
    public List<Comment> getRecipeComments(@PathVariable String recipeName){
        return commentService.getRecipeComments(recipeName);
    }
}
