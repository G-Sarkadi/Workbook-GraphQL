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

    public List<Question> getAllQuestions(Integer limit) {
        if (limit == null) {
            return questionRepository.findAll();
        }
        return questionRepository.getAllQuestionsWithLimit(PageRequest.of(0,limit));
    }

    public List<Question> getQuestionsFromModule(ModuleRoom module, Integer limit) {
        if (limit == null){
            return questionRepository.findByModule(module);
        }
        return questionRepository.findByModule(module, PageRequest.of(0,limit));
    }

    public List<Question> getQuestionsFromTopic(MainTopic mainTopic, Integer limit) {
        if (limit == null){
            return questionRepository.findByTopicName(mainTopic.getName());
        }
        return questionRepository.findByTopicName(mainTopic.getName(), PageRequest.of(0,limit));
    }

    private void init() {
        Author auth1 = authorRepository.save(Author.builder().name("Bob").build());

        Keyword keyword1 = keywordRepository.save(Keyword.builder().keyword("python").build());
        Keyword keyword2 = keywordRepository.save(Keyword.builder().keyword("java").build());
        Keyword keyword3 = keywordRepository.save(Keyword.builder().keyword("sql").build());

        Subtopic subtopic1 = subtopicRepository.save(Subtopic.builder().name("sub topic1").build());
        Subtopic subtopic2 = subtopicRepository.save(Subtopic.builder().name("sub topic2").build());
        Subtopic subtopic3 = subtopicRepository.save(Subtopic.builder().name("sub topic3").build());
        MainTopic mtp1 = MainTopic.builder()
                .name("main topic")
                .subtopics(Arrays.asList(subtopic1, subtopic2))
                .build();
        MainTopic mainTopic1 = mainTopicRepository.save(mtp1);
        MainTopic mtp2 = MainTopic.builder()
                .name("another main topic")
                .subtopics(Arrays.asList(subtopic1, subtopic3))
                .build();
        MainTopic mainTopic2 = mainTopicRepository.save(mtp2);

        Question question1 = Question.builder()
                .question("kérdés?")
                .answer("válasz!")
                .author(auth1)
                .topic(mainTopic1)
                .keywords(Arrays.asList(keyword1, keyword2))
                .module(ModuleRoom.WEB)
                .build();

        Question question2 = Question.builder()
                .question("question?")
                .answer("answer!")
                .author(auth1)
                .topic(mainTopic1)
                .keywords(Arrays.asList(keyword1, keyword3))
                .module(ModuleRoom.WEB)
                .build();

        Question question3 = Question.builder()
                .question("????")
                .answer("!!!!!")
                .author(auth1)
                .topic(mainTopic2)
                .keywords(Arrays.asList(keyword2, keyword3))
                .module(ModuleRoom.OOP)
                .build();

        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);

    }

}
