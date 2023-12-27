package me.banggusuk.sbb.answer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.banggusuk.sbb.question.Question;

import java.time.LocalDateTime;

/**
 * author : ms.Lee
 * date   : 2023-12-27
 */
@Entity
@Getter
@Setter
public class Answer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(columnDefinition = "TEXT")
  private String content;

  private LocalDateTime createDate;

  @ManyToOne
  private Question question;
}
