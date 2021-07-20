package br.com.harbitech.school.servlet;

import br.com.harbitech.school.category.Category;
import br.com.harbitech.school.category.CategoryStatus;
import br.com.harbitech.school.repository.dao.CategoryDao;
import br.com.harbitech.school.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/novaCategoria")
public class NewCategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String codeUrl = request.getParameter("codeUrl");
        String description = request.getParameter("description");
        String studyGuide = request.getParameter("studyGuide");
        String paramStatus = request.getParameter("status");
        CategoryStatus categoryStatus = CategoryStatus.valueOf(paramStatus);
        String paramOrderVisualization = request.getParameter("orderVisualization");
        Integer orderVisualization = Integer.valueOf(paramOrderVisualization);
        String iconPath = request.getParameter("iconPath");
        String htmlHexColorCode = request.getParameter("htmlHexColorCode");

        Category category = new Category(name,codeUrl,description,studyGuide,categoryStatus,orderVisualization,
                iconPath,htmlHexColorCode);

        EntityManager em = JPAUtil.getEntityManager();

        CategoryDao categoryDao = new CategoryDao(em);

        em.getTransaction().begin();
        categoryDao.save(category);
        em.getTransaction().commit();
        em.close();

        RequestDispatcher rd = request.getRequestDispatcher("/newCategory.jsp");
        request.setAttribute("categoryName", category.getName());
        rd.forward(request, response);
    }

}