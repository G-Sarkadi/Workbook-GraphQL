package com.example.workbookgraphql.repository;

import com.example.workbookgraphql.model.MainTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainTopicRepository extends JpaRepository<MainTopic, Long> {
}
