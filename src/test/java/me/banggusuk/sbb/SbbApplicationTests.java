package me.banggusuk.sbb;

import me.banggusuk.sbb.question.Question;
import me.banggusuk.sbb.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {

  @Autowired
  private QuestionRepository questionRepository;

  @Test
  void testJpa() {

    // 첫 번째 질문
    Question q1 = new Question();
    q1.setSubject( "sbb가 무엇인가요?" );
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

  @Test
  void testJpa2() {

    List<Question> all = this.questionRepository.findAll();
    assertEquals( 2, all.size() );

    Question q = all.get( 0 );
    assertEquals( "sbb가 무엇인가요?", q.getSubject() );
  }


  @Test
  void testJpa3() {

    Question q = questionRepository.findBySubject( "sbb가 무엇인가요?" );

    /*
    Optional<Question> oq = this.questionRepository.findById( 1 );
    if (oq.isPresent()) {

      Question q = oq.get();
      assertEquals( "sbb가 무엇인강요?", q.getSubject() );
    }
     */
  }

  @Test
  void testJpa4() {

    Question q =
        questionRepository.findByContent( "sbb에 대해서 알고 싶습니다." );
  }

  @Test
  void testJpa5() {

    Question q =
        questionRepository.findBySubjectAndContent(
            "sbb가 무엇인가요?",
            "sbb에 대해서 알고 싶습니다."
        );
    assertEquals( 1, q.getId() );
  }

  @Test
  void testJpa6() {

    List<Question> questionList =
        this.questionRepository.findBySubjectLike( "sbb%" );
    Question q = questionList.get( 0 );
    assertEquals( "sbb가 무엇인가요?", q.getSubject() );
  }

  @Test
  void testJpa7() {

    Optional<Question> oq = questionRepository.findById( 1 );
    assertThat( oq.isPresent() );

    Question q = oq.orElse( null );
    q.setSubject( "수정된 제목" );
    questionRepository.save( q );
  }

  @Test
  void testJpa8() {

    assertEquals( 2, this.questionRepository.count() );
    Optional<Question> oq = this.questionRepository.findById( 1 );
    assertThat( oq.isPresent() );

    Question q = oq.get();
    this.questionRepository.delete( q );
    assertEquals( 1, this.questionRepository.count() );
  }
}
