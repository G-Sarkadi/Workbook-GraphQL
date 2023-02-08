package com.example.workbookgraphql.controller;

import com.example.workbookgraphql.model.Topic;
import com.example.workbookgraphql.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class TopicController {
    private TopicService topicService;

    @QueryMapping
    public List<Topic> topics(){
        return topicService.getAll();
    }
}
