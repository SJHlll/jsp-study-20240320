package com.jsp.chap04.serivce;

import com.jsp.entity.Dancer;
import com.jsp.repository.DancerRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteSevice implements IDancerService {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String name = request.getParameter("name");
        DancerRepository.delete(name);

        // 삭제가 완료된 후 적용된 댄서 목록을 list.jsp에 보여주기
        List<Dancer> dancerList = DancerRepository.findAll();

        // list.jsp를 그대로 활용할 예정. dl이라는 일므을 똑같이 지정해야 한다
        request.setAttribute("dl", dancerList);

    }
}
