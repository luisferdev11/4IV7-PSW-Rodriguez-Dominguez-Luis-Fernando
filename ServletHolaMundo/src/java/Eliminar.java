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
public class Eliminar extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Eliminar</title>"
                    + "<link rel=\"stylesheet\" href=\"css/style.css\">");            
            out.println("</head>");
            out.println("<body class='contenedor-Eliminar'>");
                    
            try{
                int id, id_database;
            
                id = Integer.parseInt(request.getParameter("ideliminar"));

                /*
                para poder eliminar es 
                delete from nombretabla where atributo (condicion) valor
                */
                String q = "delete from mregistro where id_usu = "+id;
                
                /*
                // query para eliminar unicamente campos existentes
                String q_find = "select id_usu from mregistro where id_usu = " + id;
                set = con.createStatement();
                rs = set.executeQuery(q_find);
                
                //System.out.println(rs.getInt("id_usu"));
                
                do {
                    id_database = rs.getInt("id_usu");
                    if (id == id_database) {
                        System.out.println("El id existe en la bd");
                        break;
                    }
                } while (rs.next());
                */
                
                set.executeUpdate(q);
                System.out.println("Registro eliminado con exito");
                
                out.println("<h1>Registro Eliminado</h1>");
                
            }catch(Exception e){
                out.println("<h1>Registro No Eliminado, sucedio un error</h1>");
                System.out.println("Error al eliminar el registro");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            
            }

            out.println("<a href='index.html'>Regresar al Formulario</a>"
                    + "<br>"
                    + "<a href='Consultar'>Consultar la Tabla General de Usuarios</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    public void destroy(){
        try {
            con.close();
        } catch (Exception e) {
            super.destroy();
        }
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

}
