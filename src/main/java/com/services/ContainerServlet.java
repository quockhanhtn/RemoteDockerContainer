package com.services;

import com.jcraft.jsch.Session;
import com.model.ContainerInfo;
import com.utils.CommandUtils;
import com.utils.JSchSessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/container"})
public class ContainerServlet extends HttpServlet {
   Session session = null;

   Session getAdminSession() {
      if (session == null) {
         session = JSchSessionUtils.getInstance().getAdminSession();
      }
      return session;
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");

      String cmdResult = CommandUtils.execute(getAdminSession(), "sudo docker container ls -a");
      String[] infos = cmdResult.split("\n");
      List<ContainerInfo> containerInfos = new ArrayList<>();
      List<String> listJsonStr = new ArrayList<>();

      for (int i = 1; i < infos.length; i++) {
         containerInfos.add(new ContainerInfo(infos[i]));
      }

      for (ContainerInfo containerInfo : containerInfos) {
         listJsonStr.add(containerInfo.toJson());
      }

      try (PrintWriter out = resp.getWriter()) {
         out.write("[" + String.join(", ", listJsonStr) + "]");
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");
      String result = "";

      String action = req.getParameter("action");  //start, stop, delete
      String name = req.getParameter("name");      //container name

      if (name == null || name.equals("")) {
         result = "true";
      } else {
         name = name.trim();
         String cmd = "";
         String cmdResult = "";


         if (action.equals("delete")) {
            cmd = "sudo docker rm -f " + name;
         } else {
            cmd = "sudo docker " + action + " " + name;
         }
         cmdResult = CommandUtils.execute(getAdminSession(), cmd);
         result = cmdResult.trim().replace("\n","").equals(name) ? "true" : "false";
      }

      try (PrintWriter out = resp.getWriter()) {
         out.write(result);
      }
   }
}

