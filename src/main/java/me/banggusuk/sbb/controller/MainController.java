package me.banggusuk.sbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author : ms.Lee
 * date   : 2023-12-20
 */
@Controller
public class MainController {

  @RequestMapping(value = "/sbb")
  // 아래 함수의 리턴값을 그대로 브라우저에 표시
  // 아래 함수의 리턴가밧을 문자열화해서 브라우저 응답을 body에 담는다.
  @ResponseBody
  public String index() {

    return "안녕하세요";
  }

  /**
   * 11강 2023-12-20
   * return 값이 html로 넘어가지 않음?? -> 이렇게는 사용하지 않으니까 그리 신경쓰지 말자!!!
   * @return
   */
  @GetMapping(value = "/test")
  public String showMain() {

    return """
        <h1>안녕하세요</h1>
        <input type="text" placeholder="입력해주세요." />
        """;
  }
}
