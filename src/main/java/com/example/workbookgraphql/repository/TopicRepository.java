package com.example.workbookgraphql.repository;

import com.example.workbookgraphql.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
}
