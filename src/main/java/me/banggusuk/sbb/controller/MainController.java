package me.banggusuk.sbb.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * author : ms.Lee
 * date   : 2023-12-20
 */
@Controller
public class MainController {

  private int increaseNo = -1;

  @RequestMapping (value = "/sbb")
  // 아래 함수의 리턴값을 그대로 브라우저에 표시
  // 아래 함수의 리턴가밧을 문자열화해서 브라우저 응답을 body에 담는다.
  @ResponseBody
  public String index() {

    return "안녕하세요";
  }

  /**
   * 11강 2023-12-20
   * return 값이 html로 넘어가지 않음?? -> 이렇게는 사용하지 않으니까 그리 신경쓰지 말자!!!
   *
   * @return
   */
  @GetMapping (value = "/test")
  public String showMain() {

    return """
        <h1>안녕하세요</h1>
                <input type="text" placeholder="입력해주세요." />
        """
        ;
  }

  @GetMapping (value = "/plus")
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

  @GetMapping (value = "/increase")
  @ResponseBody
  public int showIncrease() {

    increaseNo++;
    return increaseNo;
  }

  @GetMapping (value = "/gugudan")
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
        .collect( Collectors.joining( "<br>" ) );
  }

  // mbti 구현
  @GetMapping (value = "/mbti/{name}")
  @ResponseBody
  public String showMbti(@PathVariable String name) {

    // switch 문을 간단히...
    return switch (name) {

      case "홍길순" -> {
        char j = 'J';
        yield "INF" + j;
      }
      case "임꺽정" -> "ESFJ";
      case "홍길동" , "박상원" -> "INFP";
      default -> "모름";
    };
  }

  @GetMapping (value = "/save-session/{name}/{value}")
  @ResponseBody
  public String saveSession(@PathVariable String name, @PathVariable String value, HttpServletRequest request) {

    HttpSession session = request.getSession();
    session.setAttribute( name, value );

    return "세션변수 %s의 값이 %s(으)로 설정되었습니다.".formatted( name, value );
  }

  @GetMapping (value = "/get-session/{name}")
  @ResponseBody
  public String getSession(
      @PathVariable String name,
      HttpSession session
      /*HttpServletRequest request*/) {

    // request 안에 cookies 가 포함되어 있다. -> 이것이 HttpServletRequest 를 사용하는 이유
    // request => cookies => JSESSIONID => 세션을 가져올 수 있음.

    // HttpSession을 바로 사용해도 된다.
    String value = (String) session.getAttribute( name );

    return "세션변수 %s의 값은 %s입니다.".formatted( name, value );
  }

  // 수정, 삭제 가능한 선언
  private List<Article> articles =
      new ArrayList<>(
          Arrays.asList(
              new Article( "제목", "내용" ),
              new Article( "제목", "내용" )
          )
      );

  @GetMapping (value = "/addArticle")
  @ResponseBody
  public String addArticle(String title, String body) {

    // int id = 1;
    /*
    Article article = new Article();
    article.id = id;
    article.title = title;
    article.body = body;
     */
    //Article article = new Article( id, title, body );
    Article article = new Article( title, body );
    articles.add( article );

    return "%d번 게시물이 생성되었습니다.".formatted( article.getId() );
  }

  @GetMapping(value = "/article/{id}")
  @ResponseBody
  public Article getArticle(@PathVariable int id) {

    Article article = articles.stream()
        .filter( a -> a.getId() == id )
        .findFirst()
        .orElse( null );

    return article;
  }

  @GetMapping (value = "/modifyArticle/{id}")
  @ResponseBody
  public String modifyArticle(
      @PathVariable int id,
      String title,
      String body
  ) {

    Article article = articles.stream()
        .filter( a -> a.getId() == id )
        .findFirst()
        .orElse( null );

    if (article == null) {

      return "%d번 게시물이 존재하지 않습니다.".formatted( id );
    }

    article.setTitle( title );
    article.setBody( body );

    return "%d번 게시물을 수정하였습니다.".formatted( article.getId() );
  }

  @GetMapping (value = "/deleteArticle/{id}")
  @ResponseBody
  public String deleteArticle(@PathVariable int id) {

    Article article = articles.stream()
        .filter( a -> a.getId() == id )
        .findFirst()
        .orElse( null );

    if (article == null) {

      return "%d번 게시물이 존재하지 않습니다.".formatted( id );
    }

    articles.remove( article );

    return "%d번 게시물을 삭제하였습니다.".formatted( article.getId() );
  }

  @Getter
  @Setter
  @AllArgsConstructor
  class Article {

    // Article 번호 증가
    private static int lastId = 0;

    private int id;
    private String title;
    private String body;

    public Article(String title, String body) {

      this( ++lastId, title, body );
    }
  }

  @GetMapping (value = "/addPersonOldWay")
  @ResponseBody
  public Person addPersonOldWay(int id, int age, String name) {

    Person p = new Person( id, age, name );
    return p;
  }

  @GetMapping (value = "/addPerson")
  @ResponseBody
  public Person addPerson(Person p) {

    return p;
  }

  @Getter
  @AllArgsConstructor
  class Person {

    private int id;
    private int age;
    private String name;
  }
}
