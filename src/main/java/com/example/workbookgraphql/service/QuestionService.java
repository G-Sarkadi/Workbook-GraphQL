package com.example.workbookgraphql.service;

import com.example.workbookgraphql.model.*;
import com.example.workbookgraphql.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Arrays;
import java.util.List;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;
    private AuthorRepository authorRepository;
    private KeywordRepository keywordRepository;
    private MainTopicRepository mainTopicRepository;
    private TopicRepository topicRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository,
                           AuthorRepository authorRepository,
                           KeywordRepository keywordRepository,
                           MainTopicRepository mainTopicRepository,
                           TopicRepository subtopicRepository) {
        this.questionRepository = questionRepository;
        this.authorRepository = authorRepository;
        this.keywordRepository = keywordRepository;
        this.mainTopicRepository = mainTopicRepository;
        this.topicRepository = subtopicRepository;
        init();
    }

    public List<Question> getAllQuestions(Integer limit) {
        if (limit == null) {
            return questionRepository.findAll();
        }
        return questionRepository.getAllQuestionsWithLimit(PageRequest.of(0, limit));
    }

    public List<Question> getQuestionsFromModule(ModuleRoom module, Integer limit) {
        if (limit == null) {
            return questionRepository.findByModule(module);
        }
        return questionRepository.findByModule(module, PageRequest.of(0, limit));
    }

    public List<Question> getQuestionsFromTopic(Topic topic, Integer limit) {
        if (limit == null) {
            return questionRepository.findByTopicName(topic.getName());
        }
        return questionRepository.findByTopicName(topic.getName(), PageRequest.of(0, limit));
    }


    public List<Question> getQuestionsFromMainTopic(MainTopic mainTopic, Integer limit) {
        if (limit == null){
            return questionRepository.findByTopicMainTopicName(mainTopic.getName());
        }
        return questionRepository.findByTopicMainTopicName(mainTopic.getName(), PageRequest.of(0, limit));
    }

    public List<Question> getQuestionsByKeyword(List<String> keywords, Integer limit) {
        if (limit == null) {
            return questionRepository.findByKeywords(keywords);
        }
        return questionRepository.findByKeywords(keywords, limit);
    }

    public Question addNewQuestion(Question question) {
        return questionRepository.save(question);
    }

    private void init() {
        Author author = Author.builder().name("SG").build();
        MainTopic mainTopic = MainTopic.builder().name("Computer Science").build();
        Topic topic = Topic.builder().name("Algorithms").mainTopic(mainTopic).build();
        Keyword keyword1 = Keyword.builder().name("python").build();

        Question q1 = Question.builder()
                .question("What is the purpose of a list (array in some programming languages) data structure? Name some methods of it!")
                .answer("A list can contain multiple items (strings, other lists, numbers, etc.) in one variable. The items in the list are ordered, iterable and changeable (-> the list is mutable even when some of the elements are not). The items in the list can be sorted, removed or indexed, or new items can be added to the list. We can address the list elements by their index or by their value. The syntax is: name_of_list = [item_1, item_2, item_3].")
                .author(author)
                .topic(topic)
                .keywords(Arrays.asList(keyword1))
                .module(ModuleRoom.PROGBASICS)
                .build();
        questionRepository.save(q1);



    }

}
