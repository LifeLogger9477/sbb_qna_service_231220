package me.banggusuk.sbb.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * author : ms.Lee
 * date   : 2023-12-20
 */
@Controller
public class MainController {

  private int increaseNo = -1;

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
        """
        ;
  }

  @GetMapping(value = "/plus")
  @ResponseBody
  public int showPlus(int a, int b) {

    return a + b;
  }

  @GetMapping (value = "/plus2")
  @ResponseBody
  public void showPlus2(HttpServletRequest request, HttpServletResponse response) throws IOException {

    int a = Integer.parseInt( request.getParameter( "a" ) );
    int b = Integer.parseInt( request.getParameter( "b" ) );

    response.getWriter().append( a + b + "" );
  }

  @GetMapping (value = "/minus")
  @ResponseBody
  public int showMinus(int a, int b) {

    return a - b;
  }

  @GetMapping(value = "/increase")
  @ResponseBody
  public int showIncrease() {

    increaseNo++;
    return increaseNo;
  }

  @GetMapping(value = "/gugudan")
  @ResponseBody
  public String showGugudan(Integer dan, Integer limit) {

    if (dan == null) {

      dan = 9;
    }
    if (limit == null) {

      limit = 9;
    }

    final Integer finalDan = dan;
    return IntStream.rangeClosed( 1, limit )
        .mapToObj( i -> "%d * %d = %d".formatted( finalDan, i, finalDan * i ) )
        .collect( Collectors.joining("<br>"));
  }

  //
}
