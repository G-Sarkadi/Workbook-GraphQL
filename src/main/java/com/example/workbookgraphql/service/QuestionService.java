package com.example.workbookgraphql.service;

import com.example.workbookgraphql.model.*;
import com.example.workbookgraphql.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;
    private AuthorRepository authorRepository;
    private KeywordRepository keywordRepository;
    private MainTopicRepository mainTopicRepository;
    private SubtopicRepository subtopicRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository,
                           AuthorRepository authorRepository,
                           KeywordRepository keywordRepository,
                           MainTopicRepository mainTopicRepository,
                           SubtopicRepository subtopicRepository) {
        this.questionRepository = questionRepository;
        this.authorRepository = authorRepository;
        this.keywordRepository = keywordRepository;
        this.mainTopicRepository = mainTopicRepository;
        this.subtopicRepository = subtopicRepository;
        init();
    }

    @Autowired


    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getAllQuestions(Integer limit) {
        return questionRepository.getAllQuestionsWithLimit(PageRequest.of(0,limit));
    }

    private void init() {
        Author auth1 = authorRepository.save(Author.builder().name("Bob").build());
        Keyword keyword1 = keywordRepository.save(Keyword.builder().keyword("python").build());
        Keyword keyword2 = keywordRepository.save(Keyword.builder().keyword("java").build());
        Keyword keyword3 = keywordRepository.save(Keyword.builder().keyword("sql").build());
        List<Keyword> kwlist = Arrays.asList(keyword1, keyword2, keyword3);
        Subtopic subtopic1 = subtopicRepository.save(Subtopic.builder().name("sub topic1").build());
        Subtopic subtopic2 = subtopicRepository.save(Subtopic.builder().name("sub topic2").build());
        MainTopic mainTopic = MainTopic.builder()
                .name("main topic")
                .subtopics(Arrays.asList(subtopic1, subtopic2))
                .build();
        MainTopic maintp = mainTopicRepository.save(mainTopic);

        Question question = Question.builder()
                .question("valami?")
                .answer("válasz!")
                .author(auth1)
                .topic(maintp)
                .keywords(kwlist)
                .module(ModuleRoom.WEB)
                .build();

        questionRepository.save(question);

    }
}
