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
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
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

         //リクエストスコープでedit.jspに値を渡す
         request.setAttribute("task",  t);
         request.setAttribute("_token",  request.getSession().getId());

         //タスクデータがある場合、task_idをセッションスコープに登録する
         if(t != null){
             request.getSession().setAttribute("task_id",  t.getId());
         }

         //edit.jspを呼び出す
         RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/taskList/edit.jsp");
         rd.forward(request,  response);

    }

}
