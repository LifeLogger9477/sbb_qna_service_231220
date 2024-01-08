package me.banggusuk.sbb.question;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * author : ms.Lee
 * date   : 2024-01-03
 */
public interface QuestionRepository extends JpaRepository<Question, Integer> {

  Question findBySubject(String subject);

  Question findByContent(String content);

  Question findBySubjectAndContent(String subject, String content);

  List<Question> findBySubjectLike(String subject);
}
