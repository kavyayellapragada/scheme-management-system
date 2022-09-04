package com.perceiveindia.user.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perceiveindia.user.manager.impl.SchemeManagerImpl;
import com.perceiveindia.user.model.Scheme;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SchemeServlet")
public class SchemeServlet extends HttpServlet {
    private SchemeManagerImpl schemeManager = new SchemeManagerImpl();

    private ObjectMapper objectMapper = new ObjectMapper();

    public SchemeServlet() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String n = request.getParameter("cmd2");
        ArrayList<String> schms = schemeManager.listCategoryWiseSchemes(Integer.parseInt(n));
        response.getWriter().write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(schms));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Scheme schm = objectMapper.readValue(request.getReader(), Scheme.class);
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String schm = request.getParameter("cmd3");
        try {
            schemeManager.addScheme(schm);
            PrintWriter out = response.getWriter();
            out.println("<html><body><b>Scheme Successfully Added" + "</b></body></html></br>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
