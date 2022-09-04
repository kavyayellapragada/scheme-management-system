package com.perceiveindia.user.controller;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perceiveindia.user.manager.impl.UserManagerImpl;
import com.perceiveindia.user.manager.impl.SchemeManagerImpl;
import com.perceiveindia.user.model.Scheme;
import com.perceiveindia.user.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
    private UserManagerImpl userManager = new UserManagerImpl();
    private SchemeManagerImpl schemeManager = new SchemeManagerImpl();

    private ObjectMapper objectMapper = new ObjectMapper();

    public UserDeleteServlet() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        //User usr = objectMapper.readValue(request.getReader(),User.class);
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            userManager.deleteUser(Integer.parseInt(request.getParameter("cmd1")));
            PrintWriter out = response.getWriter();
            out.println("<html><body>Deleted successfully</body></html></br>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
