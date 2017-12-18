package csrfbefore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 * ログイン Servlet
 *
 * @author A.Itou
 */
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        HttpSession session = request.getSession(true);
        String sessionId = session.getId();
        request.setAttribute("msg", sessionId);

        // JSPへForward
        String path = "/jsp/csrfbefore/login.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        request.setCharacterEncoding("UTF-8");

        // リクエストパラメーター取得
        String idVal     = request.getParameter("loginid");
        String passVal   = request.getParameter("loginpass");

        // ログイン認証
        boolean result = false;
        Users usrObj = authLogin(idVal, passVal);
        if (usrObj != null) {
            result = true;
        }

        if (result) {
            //OK
            HttpSession session = request.getSession(true);
            session.setAttribute("authFlg", "1");
            session.setAttribute("userid", usrObj.getId());

            // リダイレクト
            String url = "/javasec/csrfbefore/Mypage";
            response.sendRedirect(url);
        } else {
            //NG
            String msg = "ログインに失敗しました。";
            request.setAttribute("msg", msg);

            // JSPへForward
            String path = "/jsp/csrfbefore/login.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        }
    }

    private Users authLogin(String id, String pass) {
        Users usrObj = null;

        // DB接続設定
        Connection conn = null;
        String url = "jdbc:mysql://localhost/csrf";
        String user = "root";
        String password = "*****";
        String msg = "";
        String dbMsg = "";
        String sql = "";

        try {
            // 商品一覧取得
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, user, password);
            sql = "select id from users where userid = ? and password = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usrObj = new Users();
                usrObj.setId(rs.getInt("id"));
            }
            rs.close();
            stmt.close();
        }catch (Exception e){
            dbMsg = e.getMessage();
            System.out.println(dbMsg);
        }finally {
            try{
                if (conn != null) {
                    conn.close();
                  }
            }catch (Exception e){
            }
        }
        return usrObj;
    }
}
