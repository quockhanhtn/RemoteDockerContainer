package com.services;

import com.jcraft.jsch.Session;
import com.utils.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/ssh"})
public class SshServlet extends HttpServlet {
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String sshUsername = req.getParameter("ssh-username");
      String sshPassword = req.getParameter("ssh-password");
      Integer sshPort = StringUtils.toInt(req.getParameter("ssh-port"));

      ServletUtils.setSshSessionToHttpSession(req, null, sshUsername, sshPassword, sshPort);

      req.setAttribute("username", sshUsername);
      req.setAttribute("port", sshPort);
      ServletUtils.forward(req, resp, "./command-line.jsp");
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      int port = 1024;
      String password = "555";


      String command = req.getParameter("cmd");
      Session sshSession = ServletUtils.getSshSessionFromHttpSession(req);
      String result = CommandUtils.execute(sshSession, command);
      try (PrintWriter out = resp.getWriter()) {
         out.write(result);
      }
   }
}
