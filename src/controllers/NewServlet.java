package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //CSRF対策、ユーザーのセッションIDをリクエストスコープにセットしフォームに渡す
	    request.setAttribute("_token", request.getSession().getId());
	    //フォーム画面表示時のエラー回避のためインスタンスオブジェクトを作成、「文字数0のデータ」をフォームに渡す
	    request.setAttribute("task", new Tasks());
	    
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/taskList/new.jsp");
	    rd.forward(request,  response);
	}

}
