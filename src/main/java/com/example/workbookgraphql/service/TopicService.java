package com.example.workbookgraphql.service;

import com.example.workbookgraphql.model.Topic;
import com.example.workbookgraphql.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    private final TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }


    public List<Topic> getAll() {
        return topicRepository.findAll();
    }
}
