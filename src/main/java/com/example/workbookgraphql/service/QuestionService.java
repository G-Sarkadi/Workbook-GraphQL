package com.example.workbookgraphql.service;

import com.example.workbookgraphql.model.Question;
import com.example.workbookgraphql.repository.AuthorRepository;
import com.example.workbookgraphql.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionService {
    private QuestionRepository questionRepository;
    private AuthorRepository authorRepository;


    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getAllQuestions(Integer limit) {
        return questionRepository.getAllQuestionsWithLimit(PageRequest.of(0,limit));
    }
}
