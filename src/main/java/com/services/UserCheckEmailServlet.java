package com.services;

import com.model.UserDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/api/users/check-email"})
public class UserCheckEmailServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
      try (PrintWriter out = response.getWriter()) {
         String checkValue = request.getParameter("email");
         String result = UserDAO.getInstance().checkEmail(checkValue).toString();
         out.write(result);
      }
   }
}