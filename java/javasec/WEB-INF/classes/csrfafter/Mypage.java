package csrfafter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 * マイページ Servlet
 *
 * @author A.Itou
 */
public class Mypage extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        HttpSession session = request.getSession(true);
        String sessionId = session.getId();
        request.setAttribute("msg", sessionId);

        String authFlg = (String)session.getAttribute("authFlg");

        if (!"1".equals(authFlg)) {
            // 未ログイン
            String url = "/javasec/csrfafter/Login";
            response.sendRedirect(url);
        } else {
            // ログイン済み
            // JSPへForward
            String path = "/jsp/csrfafter/mypage.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        }
    }
}
