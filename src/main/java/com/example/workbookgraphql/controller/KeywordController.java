package com.example.workbookgraphql.controller;

import com.example.workbookgraphql.model.Keyword;
import com.example.workbookgraphql.service.KeywordService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class KeywordController {
    private KeywordService keywordService;

    @QueryMapping
    List<Keyword> keywords(){
        return keywordService.getAll();
    }
}
