package com.model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doPost(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpSession session = req.getSession();
/*
      try {
         Long id = (Long) session.getAttribute("id");

         String userType = UserDAO.getInstance().getById(id).getUserType();

         switch (userType) {
            case Cons.User.USER_TYPE_ADMIN:
               ServletUtils.forward(req, resp, "/admin");
               break;
            case Cons.User.USER_TYPE_EMPLOYEE:
               ServletUtils.forward(req, resp, "/employee");
               break;
            case Cons.User.USER_TYPE_SELLER:
               ServletUtils.forward(req, resp, "/seller");
               break;
            default:
               ServletUtils.forward(req, resp, "/view/web/login.jsp");
               break;
         }
      }
      catch (Exception e){
         try {
            String userName = req.getParameter("username");
            String password = req.getParameter("password");
            Long id = UserDAO.getInstance().checkLogin(userName, password);
            if (id != 0) {
               session.setAttribute("id", id);
               ServletUtils.forward(req, resp, "/login");
            }
            else {
               req.setAttribute("user", userName);
               req.setAttribute("pass", password);
               ServletUtils.forward(req, resp, "/view/web/login.jsp");
            }
         }
         catch (Exception e1) {
            ServletUtils.forward(req, resp, "/view/web/login.jsp");
         }
      }*/
   }
}
