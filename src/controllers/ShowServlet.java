package controllers;

import java.io.IOException;

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
 * Servlet implementation class ShowServlet
 */
@WebServlet("/show")
public class ShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //データベースと接続しindexで選択したidのデータを取得する
        EntityManager em = DBUtil.createEntityManager();
        Tasks t = em.find(Tasks.class, Integer.parseInt(request.getParameter("id")));
         em.close();

         //リクエストスコープでshow.jspにデータを渡す
         request.setAttribute("task",  t);
         RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/taskList/show.jsp");
         rd.forward(request,  response);
    }

}
