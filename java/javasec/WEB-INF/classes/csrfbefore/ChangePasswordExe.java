package csrfbefore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 * パスワード変更実行 Servlet
 *
 * @author A.Itou
 */
public class ChangePasswordExe extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        HttpSession session = request.getSession(true);
        String sessionId = session.getId();

        String authFlg = (String)session.getAttribute("authFlg");
        int    userid  = (int)session.getAttribute("userid");

        if (!"1".equals(authFlg)) {
            //　未ログイン
            String url = "/javasec/csrfbefore/Login";
            response.sendRedirect(url);
        } else {
            // パスワード変更
            boolean result = false;

            // リクエストパラメーター取得
            request.setCharacterEncoding("UTF-8");
            String newVal   = request.getParameter("newpass");
            String newReVal = request.getParameter("repass");
            if (newVal.equals(newReVal)) {
                result = changePassword(userid, newVal);
            }

            if (!result) {
                String msg = "パスワード変更に失敗しました。";
                request.setAttribute("msg", msg);
                // JSPへForward
                String path = "/jsp/csrfbefore/changepassword.jsp";
                RequestDispatcher dispatcher = request.getRequestDispatcher(path);
                dispatcher.forward(request, response);
            } else {
                String msg = "パスワード変更が完了しました。";
                request.setAttribute("msg", msg);
                // JSPへForward
                String path = "/jsp/csrfbefore/changepasswordfinish.jsp";
                RequestDispatcher dispatcher = request.getRequestDispatcher(path);
                dispatcher.forward(request, response);
            }
        }
    }

    private boolean changePassword(int userid, String newPassword){
        // DB接続設定
        Connection conn = null;
        String url = "jdbc:mysql://localhost/csrf";
        String user = "root";
        String password = "*****";
        String dbMsg = "";
        String sql = "";
        boolean result = false;

        try {
            // コメント一覧取得
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, user, password);

            sql = "update users set password = ? where id = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newPassword);
            stmt.setInt(2, userid);

            int num = stmt.executeUpdate();
            if (num > 0) {
                result = true;
            }
            conn.commit();
            stmt.close();
        }catch (Exception e){
            dbMsg = e.getMessage();
        }finally {
            try{
                if (conn != null) {
                    conn.close();
                  }
            }catch (Exception e){
            }
        }
        return result;
    }

}
