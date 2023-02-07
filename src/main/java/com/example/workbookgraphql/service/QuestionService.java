package com.example.workbookgraphql.service;

import com.example.workbookgraphql.model.*;
import com.example.workbookgraphql.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AuthorRepository authorRepository;
    private final KeywordRepository keywordRepository;
    private final MainTopicRepository mainTopicRepository;
    private final TopicRepository topicRepository;

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
//        init();
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
        // If the question is already present in the database, just return it
        Optional<Question> optionalQuestion = questionRepository.findByQuestion(question.getQuestion());
        if (optionalQuestion.isPresent()){
            return optionalQuestion.get();
        }

        // Fetch Author from the database by name if exists
        Optional<Author> optionalAuthor = authorRepository.findByName(question.getAuthor().getName());
        optionalAuthor.ifPresent(question::setAuthor);

        // Fetch Topic from the database by name if exists
        Optional<Topic> optionalTopic = topicRepository.findByName(question.getTopic().getName());
        if (optionalTopic.isPresent()){
            question.setTopic(optionalTopic.get());
        } else {
            // Check if main topic exists
            Optional<MainTopic> optionalMainTopic = mainTopicRepository.findByName(question.getTopic().getMainTopic().getName());
            optionalMainTopic.ifPresent(mainTopic -> question.setTopic(Topic.builder()
                    .name(question.getTopic().getName())
                    .mainTopic(mainTopic)
                    .build()));
        }

        // Fetch Keywords from the database if they exist
        List<Keyword> keywords = question.getKeywords();
        for (int i = 0; i < keywords.size(); i++) {
            Optional<Keyword> optionalKeyword = keywordRepository.findByName(keywords.get(i).getName());
            if (optionalKeyword.isPresent()){
                keywords.set(i, optionalKeyword.get());
            }
        }
        question.setKeywords(keywords);

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
        addNewQuestion(q1);

        Question q2 = Question.builder()
                .question("This is an entirely different question")
                .answer("This is an entirely different answer")
                .author(author)
                .topic(topic)
                .keywords(Arrays.asList(keyword1))
                .module(ModuleRoom.WEB)
                .build();
        addNewQuestion(q2);


    }

}
