package com.example.workbookgraphql.controller;

import com.example.workbookgraphql.model.*;
import com.example.workbookgraphql.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class QuestionController {
    private QuestionService questionService;

    @QueryMapping
    public List<Question> questions(@Argument Integer limit) {
        return questionService.getAllQuestions(limit);
    }

    @QueryMapping
    public List<Question> module(@Argument ModuleRoom module, @Argument Integer limit) {
        return questionService.getQuestionsFromModule(module, limit);
    }

    @QueryMapping
    public List<Question> topic(@Argument("topic") Topic topic, @Argument Integer limit) {
        return questionService.getQuestionsFromTopic(topic, limit);
    }

    @QueryMapping
    public List<Question> mainTopic(@Argument("mainTopic") MainTopic mainTopic, @Argument Integer limit) {
        return questionService.getQuestionsFromMainTopic(mainTopic, limit);
    }

    @QueryMapping
    public List<Question> keywords(@Argument("keywords") List<String> keywords, @Argument Integer limit) {
        return questionService.getQuestionsByKeyword(keywords, limit);
    }

    @SchemaMapping(typeName = "Mutation")
    public Question addQuestion(@Argument("question") Question question) {
        return questionService.addNewQuestion(question);
    }
}
