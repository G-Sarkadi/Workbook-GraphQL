package com.example.workbookgraphql.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @ManyToOne(cascade = CascadeType.ALL)
    private Topic topic;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Keyword> keywords;
    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

}
