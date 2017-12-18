package csrfafter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

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
            String url = "/javasec/csrfafter/Login";
            response.sendRedirect(url);
        } else {
            String token = createToken();
            request.setAttribute("token", token);
            session.setAttribute("token", token);

            // JSPへForward
            String path = "/jsp/csrfafter/changepassword.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        }
    }

    private String createToken() {
        String tokenString = "";
        try{
            // トークン生成
            StringBuffer buf = new StringBuffer();
            byte token[] = new byte[20];
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.nextBytes(token);
            for(int i = 0; i < token.length; i++) {
                buf.append(String.format("%02x", token[i]));
            }
            tokenString = buf.toString();
        }catch(NoSuchAlgorithmException e){
            e.getMessage();
        }

        return tokenString;
    }
}
