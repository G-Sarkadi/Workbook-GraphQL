package com.example.workbookgraphql.repository;

import com.example.workbookgraphql.model.ModuleRoom;
import com.example.workbookgraphql.model.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT q FROM Question q ORDER BY q.id ASC")
    List<Question> getAllQuestionsWithLimit(Pageable pageable);

    Optional<Question> findByQuestion(String question);

    List<Question> findByModule(ModuleRoom module);

    List<Question> findByModule(ModuleRoom module, Pageable pageable);

    List<Question> findByTopicName(String name);

    List<Question> findByTopicName(String name, Pageable pageable);

    @Query(nativeQuery = true, value= """
            SELECT DISTINCT question.id,
                   question.answer,
                   question.module,
                   question.question,
                   question.author_id,
                   question.topic_id
            FROM question
            join question_keywords qk on question.id = qk.question_id
            join keyword k on k.id = qk.keywords_id
            where k.name in :keywords""")
    List<Question> findByKeywords(List<String> keywords);

    @Query(nativeQuery = true, value= """
            SELECT DISTINCT question.id,
                   question.answer,
                   question.module,
                   question.question,
                   question.author_id,
                   question.topic_id
            FROM question
            join question_keywords qk on question.id = qk.question_id
            join keyword k on k.id = qk.keywords_id
            where k.name in :keywords
            limit :limit""")
    List<Question> findByKeywords(List<String> keywords, Integer limit);

    List<Question> findByTopicMainTopicName(String mainTopic);

    List<Question> findByTopicMainTopicName(String mainTopic, Pageable pageable);

}
