package com.jsp.chap04.controller;

import com.jsp.chap04.serivce.DeleteSevice;
import com.jsp.chap04.serivce.IDancerService;
import com.jsp.chap04.serivce.ListService;
import com.jsp.chap04.serivce.RegistService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 서블릿 == 컨트롤러
// 클라이언트의 요청을 파악하고 모델에게 로직을 위임하며
// 응답할 view를 결정한다

// 확장자 패턴 -> 앞이 뭐가 들어가도 .do로 끝나는 요청이면 이 서블릿이 다 받는다
@WebServlet("*.do")
public class DancerController extends HttpServlet {

    // 인터페이스 타입의 변수를 선언해 상황에 맞게 서비스 객체 갈아끼우기
    private IDancerService sv;
    private RequestDispatcher rd;


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        try {
            switch (requestURI) {
                case "/register.do":
                    System.out.println("등록 폼으로 이동시켜주기");
                    rd = request.getRequestDispatcher("WEB-INF/chap03/dancer/register.jsp");
                    rd.forward(request, response);
                    break;

                case "/regist.do":
                    System.out.println("댄서 등록 요청");
                    sv = new RegistService();
                    sv.execute(request,response);

                    // 서비스가 dancer 목록을 request에 담아놓은 상태이다
                    // 디스패처에게 목적지를 알려주면서 request와 response 객체를 가지고 이동하라는 명령을 내린다
                    // 실제 페이지가 목적지로 이동되면서 request와 response 객체도 함께 전달된다
                    // -> jsp에서 request를 꺼내서 목록을 화면에 뿌려서 응답하면 끝난다
                    rd = request.getRequestDispatcher("WEB-INF/chap03/dancer/list.jsp");
                    rd.forward(request, response);
                    break;

                case "/delete.do":
                    System.out.println("삭제 요청이 들어옴");
                    sv = new DeleteSevice();
                    sv.execute(request, response);
                    rd = request.getRequestDispatcher("WEB-INF/chap03/dancer/list.jsp");
                    rd.forward(request, response);
                    break;

                case "/list.do":
                    System.out.println("댄서 목록 요청이 들어옴!");
                    sv = new ListService();
                    sv.execute(request, response);
                    rd = request.getRequestDispatcher("/WEB-INF/chap03/dancer/list.jsp");
                    rd.forward(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
