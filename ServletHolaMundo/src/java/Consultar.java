/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//permite realizar la conexion con la base de datos
import java.sql.Connection;
import java.sql.DriverManager;

//permite utilizar las sentecnias de sql (create, delete, drop, update)
import java.sql.Statement;

//permite realizar las consultas con la base de datos
import java.sql.ResultSet;
import javax.servlet.ServletConfig;
/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Consultar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    //objetos para conexiones y las deás weas de BD
    private Connection con;
    private Statement set;
    private ResultSet rs;
    
    
    //vamos a crear el metodo constructor
    public void init(ServletConfig cfg) throws ServletException{
        
        // para conectarnos con la base de datos
        String url = "jdbc:mysql://localhost:3307/registro4iv7";
                    //driver:gestorBD:puerto//IP/nombreBD
                    
        String userName = "root";
        String password = "elchinodragonball";
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            // a veces el driver ya maneja por defecto el puerto, es por ello que puede
            // llegar a mandar algun error, en ese caso driver:gestorBD://IP/nombreBD
            // jdbc:mysql://localhost/registro4iv7
            
            con = DriverManager.getConnection(url, userName, password);
            set = con.createStatement();
            
            System.out.println("Se ha conectado a la base de datos");
            
        } catch (Exception e) {
            System.out.println("No se ha conectado");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Consultar</title>"
                    + "<link rel=\"stylesheet\" href=\"css/style.css\">");            
            out.println("</head>");
            out.println("<body class='contenedor'>");
            out.println("<h1 class='title-Tabla'>Tabla general de usuarios</h1>");
            out.println("<table border='2'>"
                    + "<thead>"
                        + "<tr>"
                            + "<th>ID</th>"
                            + "<th>Nombre Completo</th>"
                            + "<th>Edad</th>"
                            + "<th>Correo</th>"
                        + "</tr>"
                    + "</thead>"
                    + "<tbody>");
            
                try {

                    String nom, appat, apmat, correo, q;
                    int edad, id;
                    
                    q = "select * from mregistro";
                    
                    set = con.createStatement();
                    rs = set.executeQuery(q);
                    
                    while (rs.next()) {                        
                        id = rs.getInt("id_usu");
                        nom = rs.getString("nom_usu");
                        appat = rs.getString("appat_usu");
                        apmat = rs.getString("apmat_usu");
                        edad = rs.getInt("edad_usu");
                        correo = rs.getString("email_usu");
                        
                        out.println("<tr>"
                                + "<td>"+id+"</td>"
                                + "<td>"+nom+" "+appat+" "+apmat+"</td>"
                                + "<td>"+edad+"</td>"
                                + "<td>"+correo+"</td>"
                            + "</tr>");
                    }
                    
                    System.out.println("Consulta exitosa");
                    
                    rs.close();
                    set.close();
                    
                } catch (Exception e) {
                
                    System.out.println("Error al consultar la BD");
                    System.out.println(e.getMessage());
                    System.out.println(e.getStackTrace());
                }
            
             out.println("</tbody>"
                    + "</table>"
                    + "<br>"
                    + "<a href='index.html'>Regresar al Formulario</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
     public void destroy(){
        try {
            con.close();
        } catch (Exception e) {
            super.destroy();
        }
    }
}
