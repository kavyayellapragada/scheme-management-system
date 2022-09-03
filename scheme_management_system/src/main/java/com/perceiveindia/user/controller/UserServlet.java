package com.perceiveindia.user.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perceiveindia.user.manager.impl.UserManagerImpl;
import com.perceiveindia.user.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

// Servlet Name
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private UserManagerImpl userManager = new UserManagerImpl();

    private ObjectMapper objectMapper = new ObjectMapper();

    public UserServlet() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> users = userManager.listUsers();
        response.getWriter().write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(users));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User usr = objectMapper.readValue(request.getReader(), User.class);
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            PrintWriter out = response.getWriter();
            userManager.addUser(usr);
            out.println("<html><body><b>Successfully Inserted"
                    + "</b></body></html>");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request,HttpServletResponse  response) throws IOException
    {
        User usr = objectMapper.readValue(request.getReader(),User.class);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            userManager.deleteUser(Integer.valueOf(request.getParameter("user_id")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}