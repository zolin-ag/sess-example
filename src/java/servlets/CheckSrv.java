/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

@WebServlet(name = "CheckSrv", urlPatterns = {"/CheckSrv"})
public class CheckSrv extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");

            String name = request.getParameter("name");
            String pwd = request.getParameter("password");

            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/sess");

            Connection con;
            Statement st;
            int userId = -1;

            con = ds.getConnection();
            st = con.createStatement();
            String qu = "SELECT user_id FROM STUD.USERS WHERE name='" + name + "' AND pwd='" + pwd + "'";
            ResultSet rs = st.executeQuery(qu);
            if (rs.next()) {
                userId = rs.getInt("user_id");

            }
            con.close();
            if (userId > 0) {  // если прошли регистрацию ------------------
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);
                session.setAttribute("DataSource", ds);

                String path = "/exam.jsp";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
                requestDispatcher.forward(request, response);
            }else {       // если не прошли -----------------------------------
                try ( PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html> <head>");
                    out.println("<title>Регистрация</title> </head>");
                    out.println("<body>");
                    out.println("<h1>Не верные имя пользователя/пароль</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }

        } catch (NamingException ex) {
            Logger.getLogger(CheckSrv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CheckSrv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(CheckSrv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CheckSrv.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Сервлет проверки регистрации";
    }// </editor-fold>

}
