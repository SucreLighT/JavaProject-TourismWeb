package cn.sucre.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description:用于分发方法的Servlet
 * @author: sucre
 * @date: 2020/08/06
 * @time: 14:46
 */
@WebServlet(urlPatterns = "/BaseServlet")
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //完成方法分发
        //1.获取请求路径
        String uri = req.getRequestURI();

        //2.获取方法名称
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);

        //3.获取方法对象
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            //4.执行方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * 封装序列化json的方法：直接将传入对象序列化为字节流并写回客户端
     * @param obj
     * @param response
     * @throws IOException
     */
    public void writeValue(Object obj, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");
        mapper.writeValue(response.getOutputStream(), obj);
    }

    /**
     * 封装序列化json的方法：将对象序列化成字符并返回
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}
