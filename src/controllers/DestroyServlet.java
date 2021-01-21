package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks;
import utils.DBUtil;

/**
 * Servlet implementation class DestroyServlet
 */
@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //フォームから渡されたセッションID
        String _token = request.getParameter("_token");

        //CRF対策、セッションIDの照合
        if(_token != null && _token.equals(request.getSession().getId())){
            //セッションIDが一致した場合データベースと接続
            EntityManager em = DBUtil.createEntityManager();

            //EditServletでセッションに登録したtask_idを取得、Integer型にキャストする
            Tasks t = em.find(Tasks.class, (Integer)(request.getSession().getAttribute("task_id")));

            //該当データをデータベースから削除する
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
            em.close();

            //不要なtask_idをセッションから削除する
            request.getSession().removeAttribute("task_id");

            //IndexServletに遷移する
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }
}
