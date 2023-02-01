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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String question;
    private String answer;
    @Enumerated(EnumType.STRING)
    private ModuleRoom module;
    @ManyToOne
    private MainTopic topic;
    @OneToMany
    private List<Keyword> keywords;
    @ManyToOne
    private Author author;

}
