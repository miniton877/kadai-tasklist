package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks;
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //データベースからテーブル情報を取得する
        EntityManager em = DBUtil.createEntityManager();
        List<Tasks> taskList = em.createNamedQuery("getAllTaskList", Tasks.class).getResultList();
        em.close();

      //index.jspにリクエストスコープで値を渡す
        request.setAttribute("taskList",  taskList);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/taskList/index.jsp");
        rd.forward(request, response);
    }
}



