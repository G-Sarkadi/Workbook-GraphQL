package com.example.workbookgraphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Subtopic extends JpaRepository<Subtopic, Long> {
}
