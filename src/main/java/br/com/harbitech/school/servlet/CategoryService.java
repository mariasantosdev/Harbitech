package br.com.harbitech.school.servlet;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryDto;
import br.com.harbitech.school.repository.dao.CategoryDao;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;

@WebServlet(urlPatterns="/api/todascategorias")
public class CategoryService extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CategoryDao categoryDao = new CategoryDao();
        List<Category> categories = categoryDao.findAll();

        String valueOfHeader = request.getHeader("Accept");

        System.out.println(valueOfHeader);

        if (valueOfHeader.contains("xml")) {
            XStream xstream = new XStream();
            xstream.alias("categories", Category.class);
            String xml = xstream.toXML(categories);

            response.setContentType("application/xml");
            response.getWriter().print(xml);

        } else if (valueOfHeader.contains("json")) {
            Gson gson = new Gson();
            String json = gson.toJson(categories);

            response.setContentType("application/json");
            response.getWriter().print(json);
        } else {
            response.setContentType("application/json");
            response.getWriter().print("{'message':'no content'}");
        }
    }
}
