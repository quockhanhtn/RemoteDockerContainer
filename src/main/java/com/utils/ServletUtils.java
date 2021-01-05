package com.utils;

import com.jcraft.jsch.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ServletUtils {
   public static void forward(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
      RequestDispatcher reqDispatcher = req.getRequestDispatcher(url);
      reqDispatcher.forward(req, resp);
   }

   public static void setSshSessionToHttpSession(HttpServletRequest req, Session sshSession, String username, String password, Integer port) {
      HttpSession httpSession = req.getSession();
      if (sshSession == null) {
         if (username == null || username.equals("")) {
            sshSession = JSchSessionUtils.getAdminSession();
         } else {
            sshSession = JSchSessionUtils.getSession(username, password, port);
         }
      }
      httpSession.setAttribute("sshSession", sshSession);
      httpSession.setAttribute("sshUsername", username);
      httpSession.setAttribute("sshPassword", password);
      httpSession.setAttribute("sshPort", port);
   }

   public static Session getSshSessionFromHttpSession(HttpServletRequest req) {
      HttpSession httpSession = req.getSession();
      Session sshSession = (Session) httpSession.getAttribute("sshSession");
      if (sshSession == null) {
         String username = (String) httpSession.getAttribute("sshUsername");
         String password = (String) httpSession.getAttribute("sshPassword");
         Integer port = (Integer) httpSession.getAttribute("sshPort");
         if (username == null || username.equals("")) {
            sshSession = JSchSessionUtils.getAdminSession();
         }
         else {
            sshSession = JSchSessionUtils.getSession(username, password, port);
         }
      }
      return sshSession;
   }
}