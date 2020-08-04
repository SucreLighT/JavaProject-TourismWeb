package cn.sucre.web.servlet;

import cn.sucre.service.UserService;
import cn.sucre.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 实现激活用户的Servlet
 * @author: sucre
 * @date: 2020/08/04
 * @time: 14:33
 */
@WebServlet(urlPatterns = "/ActivateUserServlet")
public class ActivateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        // 调用service进行激活
        if(code != null){
            UserService service = new UserServiceImpl();
            boolean flag = service.activate(code);

            String msg = null;
            if(flag){
                msg = "激活成功，请<a href='login.html'>登录</a>";
            }else {
                msg = "激活失败，请联系管理员!";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
