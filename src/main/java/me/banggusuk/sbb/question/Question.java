package me.banggusuk.sbb.question;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * author : ms.Lee
 * date   : 2023-12-27
 */
@Entity
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 200)
  private String subject;

  @Column(columnDefinition = "TEXT")
  private String content;

  private LocalDateTime createDate;
}
