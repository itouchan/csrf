package csrfbefore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 * パスワード変更 Servlet
 *
 * @author A.Itou
 */
public class ChangePassword extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        HttpSession session = request.getSession(true);
        String sessionId = session.getId();

        String authFlg = (String)session.getAttribute("authFlg");

        if (!"1".equals(authFlg)) {
            //　未ログイン
            String url = "/javasec/csrfbefore/Login";
            response.sendRedirect(url);
        } else {
            // JSPへForward
            String path = "/jsp/csrfbefore/changepassword.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        }
    }
}
