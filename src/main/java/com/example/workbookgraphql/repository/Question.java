package com.example.workbookgraphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Question extends JpaRepository<Question, Long> {
}
