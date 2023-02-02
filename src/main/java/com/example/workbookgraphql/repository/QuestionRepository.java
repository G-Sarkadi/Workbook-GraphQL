package com.example.workbookgraphql.repository;

import com.example.workbookgraphql.model.ModuleRoom;
import com.example.workbookgraphql.model.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT q FROM Question q ORDER BY q.id DESC")
    List<Question> getAllQuestionsWithLimit(Pageable pageable);

    List<Question> findByModule(ModuleRoom module);

    List<Question> findByModule(ModuleRoom module, Pageable pageable);

    List<Question> findByTopicName(String name);

    List<Question> findByTopicName(String name, Pageable pageable);

    @Query(nativeQuery = true, value= """
            SELECT question.id,
                   question.answer,
                   question.module,
                   question.question,
                   question.author_id,
                   question.topic_id
            FROM question
            join main_topic mt on mt.id = question.topic_id
            join main_topic_subtopics mts on mt.id = mts.main_topic_id
            join subtopic s on mts.subtopics_id = s.id
            where s.name in :subtopics""")
    List<Question> findBySubtopicName(List<String> subtopics);

    @Query(nativeQuery = true, value= """
            SELECT question.id,
                   question.answer,
                   question.module,
                   question.question,
                   question.author_id,
                   question.topic_id
            FROM question
            join main_topic mt on mt.id = question.topic_id
            join main_topic_subtopics mts on mt.id = mts.main_topic_id
            join subtopic s on mts.subtopics_id = s.id
            where s.name in :subtopics
            limit :limit""")
    List<Question> findBySubtopicName(List<String> subtopics, Integer limit);
}
