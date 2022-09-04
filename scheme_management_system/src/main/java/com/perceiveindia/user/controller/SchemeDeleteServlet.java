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

@WebServlet("/SchemeDeleteServlet")
public class SchemeDeleteServlet extends HttpServlet {
    private SchemeManagerImpl schemeManager = new SchemeManagerImpl();

    private ObjectMapper objectMapper = new ObjectMapper();

    public SchemeDeleteServlet() throws SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse  response) throws IOException
    {
        //Scheme schm = objectMapper.readValue(request.getReader(),Scheme.class);
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String schm = request.getParameter("cmd4");
        try {
            schemeManager.deleteScheme(schm);
            PrintWriter out = response.getWriter();
            out.println("<html><body>Deleted successfully</body></html></br>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
