package csrfbefore;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

/**
 * ログアウト Servlet
 *
 * @author A.Itou
 */
public class Logout extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        //セッション　破棄
        HttpSession session = request.getSession(true);
        session.invalidate();

        // JSPへForward
        String msg = "ログアウトしました。";
        request.setAttribute("msg", msg);
        String path = "/jsp/csrfbefore/login.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }
}
