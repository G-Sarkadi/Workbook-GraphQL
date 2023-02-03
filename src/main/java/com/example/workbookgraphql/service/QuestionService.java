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
        return questionRepository.findByKeywords(keywords, PageRequest.of(0, limit));
    }

    private void init() {
        Author auth1 = authorRepository.save(Author.builder().name("SG").build());

        Keyword python = keywordRepository.save(Keyword.builder().name("python").build());
        Keyword java = keywordRepository.save(Keyword.builder().name("java").build());
        Keyword sql = keywordRepository.save(Keyword.builder().name("sql").build());

        MainTopic ComputerScience = mainTopicRepository.save(MainTopic.builder().name("Computer science").build());
        MainTopic ProgrammingLanguages = mainTopicRepository.save(MainTopic.builder().name("Programming languages").build());
        MainTopic ProgrammingParadigms = mainTopicRepository.save(MainTopic.builder().name("Programming paradigms").build());
        MainTopic SoftwareEngineering = mainTopicRepository.save(MainTopic.builder().name("Software engineering").build());
        MainTopic SoftwareDesign = mainTopicRepository.save(MainTopic.builder().name("Software design").build());
        MainTopic SoftwareDevelopment = mainTopicRepository.save(MainTopic.builder().name("Software development").build());
        MainTopic ProgrammingEnvironment = mainTopicRepository.save(MainTopic.builder().name("Programming environment").build());

        Topic Algorithm = topicRepository.save(Topic.builder().name("Algorithms").mainTopic(ComputerScience).build());
        Topic DataStructures = topicRepository.save(Topic.builder().name("Data structures").mainTopic(ComputerScience).build());
        Topic Database = topicRepository.save(Topic.builder().name("Database").mainTopic(ComputerScience).build());
        Topic Other = topicRepository.save(Topic.builder().name("Other").mainTopic(ComputerScience).build());
        Topic Python = topicRepository.save(Topic.builder().name("Python").mainTopic(ProgrammingLanguages).build());
        Topic SQL = topicRepository.save(Topic.builder().name("SQL").mainTopic(ProgrammingLanguages).build());


        questionRepository.save(Question.builder()
                .question("What is the purpose of a list (array in some programming languages) data structure? Name some methods of it!")
                .answer("A list can contain multiple items (strings, other lists, numbers, etc.) in one variable. The items in the list are ordered, iterable and changeable (-> the list is mutable even when some of the elements are not). The items in the list can be sorted, removed or indexed, or new items can be added to the list. We can address the list elements by their index or by their value. The syntax is: name_of_list = [item_1, item_2, item_3].")
                .author(auth1)
                .topic(DataStructures)
                .keywords(Arrays.asList(python))
                .module(ModuleRoom.PROGBASICS)
                .build());

        questionRepository.save(Question.builder()
                .question("What is the difference between a list/array and a set?")
                .answer("The set can only contain a value once, while the list can contain it multiple times. The set items are unordered while the list items are ordered. Both are iterables, but because sets are unordered, can't access their values by indexing. Set items are unchangeable but we can remove or add items later. Sets can be used to filter out multiple instances from a list. The syntax is: name_of_set = {item_1, item_2, item_3}")
                .author(auth1)
                .topic(DataStructures)
                .keywords(Arrays.asList(python))
                .module(ModuleRoom.PROGBASICS)
                .build());

        questionRepository.save(Question.builder()
                .question("What is the purpose and methods of a dictionary/map data structure")
                .answer("A dictionary holds data in key/value pairs. We can access it by calling a key in the dictionary and it returns the data stored in the value. Those key/value pairs can be removed, changed or new pairs can added. From Python 3.6 dictionaries are ordered. The syntax is: name_of_dictionary = {key_1: value_1, key_2: value_2, key_3: value_3}")
                .author(auth1)
                .topic(SQL)
                .keywords(Arrays.asList(python))
                .module(ModuleRoom.PROGBASICS)
                .build());



    }

}
