package me.banggusuk.sbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author : ms.Lee
 * date   : 2023-12-20
 */
@Controller
public class MainController {

  @RequestMapping(value = "/sbb")
  public void index() {

    System.out.println("첫 시작");
  }
}
