package com.services;

import com.jcraft.jsch.Session;
import com.utils.CommandUtils;
import com.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/admin-ssh"})
public class AdminSshServlet extends HttpServlet{
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      ServletUtils.setSshSessionToHttpSession(req, null, null, null, null);

      req.setAttribute("username", "admin");
      req.setAttribute("port", "22");

      ServletUtils.forward(req, resp, "./admin-command-line.jsp");
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String command = req.getParameter("cmd");
      Session sshSession = ServletUtils.getSshSessionFromHttpSession(req);

      //CommandUtils.createSshContainer(sshSession, "test_java3",9092,"1234");

      String result = CommandUtils.execute(sshSession, command);
      try (PrintWriter out = resp.getWriter()) {
         out.write(result);
      }
   }
}
