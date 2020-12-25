package api.services;

import api.utils.CommandUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(urlPatterns = {"/api/container"})
public class ContainerServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String name, action;
      String cmd = "", execResult = "", result = "false";
      try {
         name = req.getParameter("name");
         action = req.getParameter("action");

         if (action.equals("start") || action.equals("stop")) {
            cmd = "sudo docker " + action + " " + name;
            execResult = CommandUtils.Execute(cmd);
            result = execResult.contains(name) ? "true" : "false";

         } else {   // check status
            cmd = "sudo docker ps --filter \"name=" + name + "\"";
            execResult = CommandUtils.Execute(cmd);
            result = Arrays.asList(execResult.split("\n")).get(1).contains(name) ? "true" : "false";
         }
      } catch (Exception e) {
         result = e.getMessage();
      }
      resp.setContentType("text/html; charset=UTF-8");
      try (PrintWriter out = resp.getWriter()) {
         out.write(result + "\n" +execResult);
      }
   }
}

