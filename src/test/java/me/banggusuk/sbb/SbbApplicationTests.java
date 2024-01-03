package me.banggusuk.sbb;

import me.banggusuk.sbb.question.Question;
import me.banggusuk.sbb.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class SbbApplicationTests {

  @Autowired
  private QuestionRepository questionRepository;

  @Test
  void testJpa() {

    // 첫 번째 질문
    Question q1 = new Question();
    q1.setSubject( "sbb가 무엇인강요?" );
    q1.setContent( "sbb에 대해서 알고 싶습니다." );
    q1.setCreateDate( LocalDateTime.now() );
    this.questionRepository.save( q1 );

    System.out.println("Question 생성 ID: " + q1.getId());

    // 두 번째 질문
    Question q2 = new Question();
    q2.setSubject( "스프링부트 모델 질문입니다." );
    q2.setContent( "id는 자동으로 생성되나요?" );
    q2.setCreateDate( LocalDateTime.now() );
    this.questionRepository.save( q2 );

    assertThat( q1.getId() ).isGreaterThan( 0 );
    assertThat( q2.getId() ).isGreaterThan( q1.getId() );
  }

}
