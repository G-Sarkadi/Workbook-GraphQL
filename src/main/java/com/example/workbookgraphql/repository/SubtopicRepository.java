package com.example.workbookgraphql.repository;

import com.example.workbookgraphql.model.Subtopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtopicRepository extends JpaRepository<Subtopic, Long> {
}
