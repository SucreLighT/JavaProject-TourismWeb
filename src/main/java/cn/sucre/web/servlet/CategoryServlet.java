package cn.sucre.web.servlet;

import cn.sucre.domain.Category;
import cn.sucre.service.CategoryService;
import cn.sucre.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * @description:分类的Servlet
 * @author: sucre
 * @date: 2020/08/07
 * @time: 10:19
 */
@WebServlet(urlPatterns = "/Category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryServiceImpl();

    /**
     * 查询所有的分类条目
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用service查询所有
        List<Category> categories = categoryService.findAll();
        //2.序列化json返回
        writeValue(categories, response);
    }

}
