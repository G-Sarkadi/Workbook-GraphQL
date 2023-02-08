package com.example.workbookgraphql.controller;

import com.example.workbookgraphql.model.MainTopic;
import com.example.workbookgraphql.service.MainTopicService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class MainTopicController {
    private MainTopicService mainTopicService;

    @QueryMapping
    List<MainTopic> mainTopics(){
        return mainTopicService.getAll();
    }
}
