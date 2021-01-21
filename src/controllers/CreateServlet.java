package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks;
import utils.DBUtil;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
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
            EntityManager em =DBUtil.createEntityManager();
            //モデル(DTO)をインスタンス化し、インスタンスにフォームから渡されたコンテントをセットする
            Tasks t = new Tasks();
            String content = request.getParameter("content");
            t.setContent(content);

            //作成日時、更新日時の取得
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            t.setCreated_at(currentTime);
            t.setUpdated_at(currentTime);

            //取得したデータをデータベースに保存する
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            em.close();

            //IndexServletに遷移する
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }
}
