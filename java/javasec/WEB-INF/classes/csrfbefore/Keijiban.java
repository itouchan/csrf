package csrfbefore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 * 掲示板 Servlet
 *
 * @author A.Itou
 */
public class Keijiban extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        // コメント一覧
        ArrayList<Comments> result = getCommentList();
        request.setAttribute("list", result);

        // JSPへForward
        String path = "/jsp/csrfbefore/keijiban.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        // リクエストパラメーター取得
        String msgVal = request.getParameter("msg");
        String speakerVal   = request.getParameter("speaker");

        // 登録
        boolean result = false;
        if (msgVal != null && msgVal.length() != 0
        && speakerVal != null && speakerVal.length() != 0) {
            result = regComments(msgVal, speakerVal);
        }

        // リダイレクト
        String url = "/javasec/csrfbefore/Keijiban";
        response.sendRedirect(url);
    }

    private ArrayList<Comments> getCommentList(){
        // DB接続設定
        Connection conn = null;
        String url = "jdbc:mysql://localhost/csrf";
        String user = "root";
        String password = "*****";
        String msg = "";
        String dbMsg = "";
        ArrayList<Comments> result = new ArrayList<Comments>();
        String sql = "";

        try {
            // コメント一覧取得
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            sql = "SELECT * FROM comments ORDER BY id DESC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                // オブジェクト設定
                Comments cmtEty = new Comments();
                cmtEty.setId(rs.getInt("id"));
                cmtEty.setMsg(rs.getString("msg"));
                cmtEty.setSpeaker(rs.getString("speaker"));
                result.add(cmtEty);
            }
            rs.close();
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

    private boolean regComments(String msg, String speaker){
        // DB接続設定
        Connection conn = null;
        String url = "jdbc:mysql://localhost/csrf";
        String user = "root";
        String password = "560113Po()";
        String dbMsg = "";
        String sql = "";
        boolean result = false;

        try {
            // コメント一覧取得
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, user, password);

            sql = "insert into comments (msg, speaker) values (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, msg);
            stmt.setString(2, speaker);

            int num = stmt.executeUpdate();
            if (num > 0) {
                result = true;
            }

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
