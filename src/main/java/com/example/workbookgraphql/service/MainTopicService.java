package com.example.workbookgraphql.service;

import com.example.workbookgraphql.model.MainTopic;
import com.example.workbookgraphql.repository.MainTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainTopicService {
    private final MainTopicRepository mainTopicRepository;

    @Autowired
    public MainTopicService(MainTopicRepository mainTopicRepository) {
        this.mainTopicRepository = mainTopicRepository;
    }


    public List<MainTopic> getAll() {
        return mainTopicRepository.findAll();
    }
}
