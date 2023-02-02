package com.example.workbookgraphql.controller;

import com.example.workbookgraphql.model.MainTopic;
import com.example.workbookgraphql.model.ModuleRoom;
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
        return questionService.getAllQuestions(limit);
    }

    @QueryMapping
    public List<Question> module(@Argument ModuleRoom module, @Argument Integer limit) {
        return questionService.getQuestionsFromModule(module, limit);
    }

    @QueryMapping
    public List<Question> mainTopics(@Argument("mainTopicInput") MainTopic mainTopic, @Argument Integer limit) {
        return questionService.getQuestionsFromTopic(mainTopic, limit);
    }
}
