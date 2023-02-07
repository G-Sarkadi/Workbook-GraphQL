package com.example.workbookgraphql.repository;

import com.example.workbookgraphql.model.MainTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MainTopicRepository extends JpaRepository<MainTopic, Long> {
    Optional<MainTopic> findByName(String name);
}
