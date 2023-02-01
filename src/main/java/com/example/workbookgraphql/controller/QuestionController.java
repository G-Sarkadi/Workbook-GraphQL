package com.example.workbookgraphql.controller;

import com.example.workbookgraphql.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.example.workbookgraphql.model.Question;

import java.util.List;

@Controller
@AllArgsConstructor
public class QuestionController {
    private QuestionService questionService;

    @QueryMapping
    public List<Question> questions(@Argument Integer limit ) {
        if (limit == null) {
            return questionService.getAllQuestions();
        }
        return questionService.getAllQuestions(limit);
    }
}
