package com.perceiveindia.user.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perceiveindia.user.manager.impl.SchemeManagerImpl;
import com.perceiveindia.user.model.Scheme;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SchemeServlet extends HttpServlet {
    private SchemeManagerImpl schemeManager = new SchemeManagerImpl();

    private ObjectMapper objectMapper = new ObjectMapper();

    public SchemeServlet() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Scheme> schms = schemeManager.listCategoryWiseSchemes("sports");
        response.getWriter().write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(schms));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Scheme schm = objectMapper.readValue(request.getReader(), Scheme.class);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            schemeManager.addScheme(schm,"sports");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request,HttpServletResponse  response) throws IOException
    {
        Scheme schm = objectMapper.readValue(request.getReader(),Scheme.class);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            schemeManager.deleteScheme(Integer.valueOf(request.getParameter("id")),"sports");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
