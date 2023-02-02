package com.example.workbookgraphql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String keyword;
}
