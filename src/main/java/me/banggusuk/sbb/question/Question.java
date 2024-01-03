package me.banggusuk.sbb.question;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.banggusuk.sbb.answer.Answer;

import java.time.LocalDateTime;
import java.util.List;

/**
 * author : ms.Lee
 * date   : 2023-12-27
 */
@Entity
@Getter
@Setter
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 200)
  private String subject;

  @Column(columnDefinition = "TEXT")
  private String content;

  private LocalDateTime createDate;

  @OneToMany (mappedBy = "question", cascade = CascadeType.REMOVE)
  private List<Answer> answerList;
}
