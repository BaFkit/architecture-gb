package ru.gb.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/UserListServlets")
public class UserListServlets extends HttpServlet {

    private Connection connection;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        connection = (Connection) context.getAttribute("jdbcConnection");
        if (connection == null) {
            throw new ServletException("No JDBC connection in Servlet Context");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                resp.getWriter().println("<p> " + rs.getLong(1) + ", " + rs.getString(2) + "</p>");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
