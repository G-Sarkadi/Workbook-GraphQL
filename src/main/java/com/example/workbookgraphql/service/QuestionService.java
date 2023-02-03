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
        return questionRepository.getAllQuestionsWithLimit(PageRequest.of(0, limit));
    }

    public List<Question> getQuestionsFromModule(ModuleRoom module, Integer limit) {
        if (limit == null) {
            return questionRepository.findByModule(module);
        }
        return questionRepository.findByModule(module, PageRequest.of(0, limit));
    }

    public List<Question> getQuestionsFromTopic(MainTopic mainTopic, Integer limit) {
        if (limit == null) {
            return questionRepository.findByTopicName(mainTopic.getName());
        }
        return questionRepository.findByTopicName(mainTopic.getName(), PageRequest.of(0, limit));
    }

    public List<Question> getQuestionsFromSubTopic(List<String> input, Integer limit) {
        if (limit == null) {
            return questionRepository.findBySubtopicName(input);
        }
        return questionRepository.findBySubtopicName(input, limit);
    }

    public List<Question> getQuestionsByKeyword(List<String> keywords, Integer limit) {
        if (limit == null) {
            return questionRepository.findByKeywords(keywords);
        }
        return questionRepository.findByKeywords(keywords, PageRequest.of(0, limit));
    }

    private void init() {
        Author auth1 = authorRepository.save(Author.builder().name("SG").build());

        Keyword keyword1 = keywordRepository.save(Keyword.builder().name("python").build());
        Keyword keyword2 = keywordRepository.save(Keyword.builder().name("java").build());
        Keyword keyword3 = keywordRepository.save(Keyword.builder().name("sql").build());

        Subtopic subtopicDataStruct = subtopicRepository.save(Subtopic.builder().name("Data structures").build());
        Subtopic subtopicAlgorithm = subtopicRepository.save(Subtopic.builder().name("Algorithms").build());
        Subtopic subtopicDatabase = subtopicRepository.save(Subtopic.builder().name("Database").build());
        Subtopic subtopicOther = subtopicRepository.save(Subtopic.builder().name("Other").build());
        Subtopic subtopicPython = subtopicRepository.save(Subtopic.builder().name("Python").build());
        Subtopic subtopicSQL = subtopicRepository.save(Subtopic.builder().name("SQL").build());
        Subtopic subtopicHtmlCss = subtopicRepository.save(Subtopic.builder().name("HTML/CSS").build());
        Subtopic subtopicJavaScript = subtopicRepository.save(Subtopic.builder().name("JavaScript").build());
        Subtopic subtopicJava = subtopicRepository.save(Subtopic.builder().name("Java").build());
        Subtopic subtopicSpring = subtopicRepository.save(Subtopic.builder().name("Spring").build());
        Subtopic subtopicProceduralProgr = subtopicRepository.save(Subtopic.builder().name("Procedural programming").build());
        Subtopic subtopicFunctional = subtopicRepository.save(Subtopic.builder().name("Functional programming").build());
        Subtopic subtopicOOP = subtopicRepository.save(Subtopic.builder().name("Object-oriented programming").build());
        Subtopic subtopicArchitectures = subtopicRepository.save(Subtopic.builder().name("Architectures").build());
        Subtopic subtopicDebugging = subtopicRepository.save(Subtopic.builder().name("Debugging").build());
        Subtopic subtopicVersionControl = subtopicRepository.save(Subtopic.builder().name("Version control").build());
        Subtopic subtopicDevOps = subtopicRepository.save(Subtopic.builder().name("DevOps").build());
        Subtopic subtopicTesting = subtopicRepository.save(Subtopic.builder().name("Testing").build());
        Subtopic subtopicNetwork = subtopicRepository.save(Subtopic.builder().name("Network").build());
        Subtopic subtopicSoftwareMeth = subtopicRepository.save(Subtopic.builder().name("Software methodologies").build());
        Subtopic subtopicOrmJpa= subtopicRepository.save(Subtopic.builder().name("ORM/JPA").build());
        Subtopic subtopicCleanCode = subtopicRepository.save(Subtopic.builder().name("Clean code").build());
        Subtopic subtopicErrorHandling = subtopicRepository.save(Subtopic.builder().name("Error handling").build());
        Subtopic subtopicSecurity = subtopicRepository.save(Subtopic.builder().name("Security").build());
        Subtopic subtopicThreaded = subtopicRepository.save(Subtopic.builder().name("Threaded programming").build());
        Subtopic subtopicSoftwareDev = subtopicRepository.save(Subtopic.builder().name("Software development methodologies").build());
        Subtopic subtopicUnixLinux = subtopicRepository.save(Subtopic.builder().name("Unix/Linux").build());

        MainTopic mtp1 = MainTopic.builder()
                .name("Computer science")
                .subtopics(Arrays.asList(subtopicAlgorithm, subtopicDataStruct, subtopicDatabase, subtopicOther))
                .build();
        MainTopic ComputerScience = mainTopicRepository.save(mtp1);
        MainTopic mtp2 = MainTopic.builder()
                .name("Programming languages")
                .subtopics(Arrays.asList(subtopicPython, subtopicSQL, subtopicHtmlCss, subtopicJavaScript, subtopicJava, subtopicSpring))
                .build();
        MainTopic ProgrammingLanguages = mainTopicRepository.save(mtp2);
        MainTopic mtp3 = MainTopic.builder()
                .name("Programming paradigms")
                .subtopics(Arrays.asList(subtopicProceduralProgr, subtopicFunctional, subtopicOOP))
                .build();
        MainTopic ProgrammingParadigms = mainTopicRepository.save(mtp3);
        MainTopic mtp4 = MainTopic.builder()
                .name("Computer science")
                .subtopics(Arrays.asList(subtopicArchitectures, subtopicDebugging, subtopicVersionControl, subtopicDevOps, subtopicTesting, subtopicNetwork, subtopicSoftwareMeth, subtopicOrmJpa))
                .build();
        MainTopic SowtwareEngineering = mainTopicRepository.save(mtp4);
        MainTopic mtp5 = MainTopic.builder()
                .name("Software design")
                .subtopics(Arrays.asList(subtopicCleanCode, subtopicErrorHandling, subtopicSecurity, subtopicThreaded))
                .build();
        MainTopic SoftwareDesign = mainTopicRepository.save(mtp5);
        MainTopic mtp6 = MainTopic.builder()
                .name("Software development")
                .subtopics(Arrays.asList(subtopicSoftwareDev))
                .build();
        MainTopic SoftwareDevelopment = mainTopicRepository.save(mtp6);
        MainTopic mtp7 = MainTopic.builder()
                .name("Programming environment")
                .subtopics(Arrays.asList(subtopicUnixLinux))
                .build();
        MainTopic ProgrammingEnvironment = mainTopicRepository.save(mtp7);

        questionRepository.save(Question.builder()
                .question("What is the purpose of a list (array in some programming languages) data structure? Name some methods of it!")
                .answer("""
                        A list can contain multiple items (strings, other lists, numbers, etc.) in one variable. The items in the list are ordered, iterable and changeable (-> the list is mutable even when some of the elements are not). The items in the list can be sorted, removed or indexed, or new items can be added to the list. We can address the list elements by their index or by their value.\tThe syntax is:   name_of_list = [item_1, item_2, item_3].

                        my_list.remove(<value>)         # Removes item with the given value
                        my_list.pop(<index>)            # Removes item with the given index
                        my_list.append(<value>)         # Adds item to the last place
                        my_list.insert(<index>, <value>)# Adds item to the given index
                        my_list[<index>]                # Returns value of given index
                        my_list.index(<value>)          # Returns index of given value
                        my_list[<index>] = <value>      # Redefines item on the given index to the given value
                        len(my_list)                    # Returns list length
                        my_list.sort()                  # Sorts list in place""")
                .author(auth1)
                .topic(ComputerScience)
                .keywords(Arrays.asList(keyword1, keyword2))
                .module(ModuleRoom.PROGBASICS)
                .build());

    }

}
