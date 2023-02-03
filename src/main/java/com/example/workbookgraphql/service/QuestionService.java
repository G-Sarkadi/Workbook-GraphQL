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
        Topic ProceduralProgr = topicRepository.save(Topic.builder().name("Procedural programming").mainTopic(ProgrammingParadigms).build());
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
                .topic(DataStructures)
                .keywords(Arrays.asList(python))
                .module(ModuleRoom.PROGBASICS)
                .build());

        questionRepository.save(Question.builder()
                .question("Fibonacci sequences. Write a method (or pseudo code), that generates the Fibonacci sequences.")
                .answer("- First we ask for a number n, where n is integer and n > 0. From now on we have multiple ways to generate a sequence: - Recursive: Define a function called fib with a parameter n. If n == 1 return 0, elif n == 2 return 1. The general case is to return fib(n-1) + fib(n-2). If you need the sequence, in a for loop in range(1,n + 1) print out the fib(i) for every iteration, or add it to a predefined empty list. If you just need the nth element just pass it in the function as an argument. - Iterative: Define a function called fib with a parameter n. Define a fib_list = [0, 1], write a for loop in range(n-2), in every iteration appends (fib_list[-1] + fib_list[-2]) to the fib_list. In the end, return the fib_list. Print out fib_list for the sequence or fib_list[-1] for the nth number.")
                .author(auth1)
                .topic(Algorithm)
                .keywords(Arrays.asList(python))
                .module(ModuleRoom.PROGBASICS)
                .build());

        questionRepository.save(Question.builder()
                .question("How do you find a max value in a list/array if you can’t use any built-in functions?")
                .answer("We define a function with parameter 'arr'. Then define a variable 'maxi' with None value. Write a for loop to go through the 'arr' list and compare every item in it with the 'maxi' variable. If 'maxi' is None or the current iteration is bigger than 'maxi', redefine 'maxi' to the item. At the end, return 'maxi'.")
                .author(auth1)
                .topic(Algorithm)
                .keywords(Arrays.asList(python))
                .module(ModuleRoom.PROGBASICS)
                .build());

        questionRepository.save(Question.builder()
                .question("How do you find the average of values in a list/array if you can’t use any built-in functions?")
                .answer("We define a function with parameter 'arr'. If 'arr' == [], return None. Else make two variables ('sum' and 'count') both with value 0. Write a for loop to go through the elements of 'arr'. In every turn, add the value of the current iteration to the 'sum' variable and add 1 to the 'count' variable. After the loop, return 'sum' / 'count'.")
                .author(auth1)
                .topic(Algorithm)
                .keywords(Arrays.asList(python))
                .module(ModuleRoom.PROGBASICS)
                .build());

        questionRepository.save(Question.builder()
                .question("What do we call an *in-place* sort?")
                .answer("When we want to sort a list, we can use the .sort() function on it. This will sorts the given list (alphabetically or numerically). Without making a copy of it to another variable or returning the sorted list it just changes the original list. We can also use the new_list = sorted(old_list) method but that creates a new object.")
                .author(auth1)
                .topic(Algorithm)
                .keywords(Arrays.asList(python))
                .module(ModuleRoom.PROGBASICS)
                .build());

        questionRepository.save(Question.builder()
                .question("Explain an algorithm which sorts a list!")
                .answer("- We define a function with an input_list as an argument. - If the length of the input_list is less than 2, just return the input_list. With that we can filter out an empty list. Later in the function we are using indexing and on an empty list that would rase an index out of range error. Plus a list with zero or one item is already sorted so we don't have to do anything with it. - Otherwise we jump to the general case: in the function we define an empty output_list. We start a for loop in range(len(input_list)). Inside it we define a variable with the first value of input_list. We loop through the input_list with a nested for loop and compare every item in it with the variable. If the item is smaller than the variable, redefine the variable to it. With this method we've found the smallest item in the input_list and wrote it in our variable. Now we append the variable to the output_list and remove it from the input_list. - After that a new cycle starts on the outer for loop and it goes through the input_list again, but we already found and deleted the smallest item in it so it will find the next smallest one. This continues as many times as the length of the input_list. At the end, the input_list will be empty, an the output_list will contain all the elements of the input_list in ascending order. We return the output_list. - Because of the nested for loops this algorithm is not very fast, but hey, it's working!")
                .author(auth1)
                .topic(Algorithm)
                .keywords(Arrays.asList(python))
                .module(ModuleRoom.PROGBASICS)
                .build());

        questionRepository.save(Question.builder()
                .question("What is the call stack?")
                .answer("A stack is a list of operations. Elements are added (pushed) or removed (poped) only from the top (last in, first out) and the stack can return the top element (peek). A stack pointer indicates the extent of the stack, adjusting as elements are pushed or popped to a stack. A call stack of a program is a stack that stores information about the currently running functions and stores the local variables. The program keeps track of its place in a script that calls multiple functions with a call stack. When a function is called, the program pushes it to the stack and starts to run it. If the function has arguments, local variables will be created from them on the given stack frame. If that function calls another function, the first func is halted and the second func is pushed to the stack. The second func runs and when it's finished, popped from the stack and (it may) returns a value. The first func receives that value (if there is any), resumes to run and popped when finished. When a function is popped, its local variables are discarded. A program basically starts with an empty call stack, during running it pushes functions on the stack and working from the top it tries to execute and pop those functions and empty out the call stack again.")
                .author(auth1)
                .topic(ProceduralProgr)
                .keywords(Arrays.asList())
                .module(ModuleRoom.PROGBASICS)
                .build());


    }

}
