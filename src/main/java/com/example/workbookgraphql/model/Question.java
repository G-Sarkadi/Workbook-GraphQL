package com.example.workbookgraphql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String question;
    @Column(columnDefinition="TEXT")
    private String answer;
    @Enumerated(EnumType.STRING)
    private ModuleRoom module;
    @ManyToOne
    private Topic topic;
    @ManyToMany
    private List<Keyword> keywords;
    @ManyToOne
    private Author author;

}
