package com.example.workbookgraphql.service;

import com.example.workbookgraphql.model.Keyword;
import com.example.workbookgraphql.repository.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordService {
    private final KeywordRepository keywordRepository;

    @Autowired
    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }


    public List<Keyword> getAll() {
        return keywordRepository.findAll();
    }
}
