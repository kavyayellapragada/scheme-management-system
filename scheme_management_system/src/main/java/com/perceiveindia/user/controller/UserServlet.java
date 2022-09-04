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

// Servlet Name
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private UserManagerImpl userManager = new UserManagerImpl();
    private SchemeManagerImpl schemeManager = new SchemeManagerImpl();

    private ObjectMapper objectMapper = new ObjectMapper();

    public UserServlet() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String n = request.getParameter("cmd");
        List<User> users = userManager.listUsers(Integer.valueOf(n));
        response.getWriter().write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(users));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //User usr = objectMapper.readValue(request.getReader(), User.class);
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String role = request.getParameter("role");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String marital_status = request.getParameter("marital_status");
        String category = request.getParameter("category");
        String education = request.getParameter("education");
        String employement = request.getParameter("employment");
        String annual_income = request.getParameter("annual_income");
        String scheme = request.getParameter("scheme");
        User usr = new User(role,gender,age,marital_status,category,education,employement,annual_income,scheme);
        try {
            PrintWriter out = response.getWriter();
            userManager.addUser(usr);
            out.println("<html><body><b>Data Successfully Inserted" + "</b></body></html></br>");
            out.println("<html><body><b>Retrieving relevant schemes information" + "</b></body></html></br>");
            ArrayList<String> res_schemes = schemeManager.searchSchemes(scheme);
            if(res_schemes.isEmpty()){
                out.println("<html><body><b>No scheme found with selected filters" + "</b></body></html>");
            }
            else{
                out.println("<html><body><b>List of Applicable Schemes" + "</b></body></html></br>");
                for(int s=0;s<res_schemes.size();s++){
                    out.println("<html><body>"+ res_schemes.get(s) + "</body></html></br>");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}