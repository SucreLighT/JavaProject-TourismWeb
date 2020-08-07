package cn.sucre.web.servlet;

import cn.sucre.domain.ResultInfo;
import cn.sucre.domain.User;
import cn.sucre.service.UserService;
import cn.sucre.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @description: 用户相关的Servlet方法集合
 * @author: sucre
 * @date: 2020/08/06
 * @time: 14:47
 */
@WebServlet(urlPatterns = "/User/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * 注册功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 校验验证码，比较session和request中的验证码是否相同
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");

        // 为了保证验证码只使用一次
        session.removeAttribute("CHECKCODE_SERVER");

        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            //验证码错误
            ResultInfo info = new ResultInfo();
            info.setErrorMsg("验证码错误！");
            info.setFlag(false);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);

            return;
        }

        // 1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        // 2.封装对象
        User user = new User();

        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 3.调用service完成注册
        // UserService userService = new UserServiceImpl();
        boolean flag = userService.regist(user);

        // 4.返回结果并相应
        ResultInfo info = new ResultInfo();
        if (flag) {
            info.setFlag(true);
        } else {
            info.setFlag(false);
            info.setErrorMsg("用户名存在，注册失败！");
        }

        // 将info对象序列化为json
        // ObjectMapper mapper = new ObjectMapper();
        // String json = mapper.writeValueAsString(info);

        String json = writeValueAsString(info);

        // 将json数据写回客户端并设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 登录功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();

        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // UserService service = new UserServiceImpl();
        User u = userService.login(user);

        ResultInfo info = new ResultInfo();
        if (u == null) {
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误！");
        }
        if (u != null && !"Y".equals(u.getStatus())) {
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，清先激活！");
        }
        if (u != null && "Y".equals(u.getStatus())) {
            request.getSession().setAttribute("user", u);
            info.setFlag(true);
        }

        writeValue(info, response);
        // ObjectMapper mapper = new ObjectMapper();
        // response.setContentType("application/json; charset=UTF-8");
        // mapper.writeValue(response.getOutputStream(), info);
    }

    /**
     * 查询获取单个用户功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOneUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取登录用户
        Object user = request.getSession().getAttribute("user");

        writeValue(user, response);
        // ObjectMapper mapper = new ObjectMapper();
        // response.setContentType("application/json;charset=utf-8");
        // mapper.writeValue(response.getOutputStream(), user);
    }

    /**
     * 登出功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁session
        request.getSession().invalidate();

        //2.跳转登录页面
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    /**
     * 激活用户功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void activate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        // 调用service进行激活
        if (code != null) {
            // UserService service = new UserServiceImpl();
            boolean flag = userService.activate(code);

            String msg = null;
            if (flag) {
                msg = "激活成功，请<a href='" + request.getContextPath() + "/login.html'>登录</a>";
            } else {
                msg = "激活失败，请联系管理员!";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }
}
