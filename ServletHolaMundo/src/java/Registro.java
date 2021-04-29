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
public class Registro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    // es necesario inicializar los elementos del servlet para que pueda conectarse con la base de datos
    // vamos a necesitar tres objetos que vengan de la clase sql
    
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String nom, appat, apmat, correo, ip, iph;
            int edad, puerto, puertoh;
            
            ip = request.getLocalAddr();
            puerto = request.getLocalPort();
            
            iph = request.getRemoteAddr();
            puertoh = request.getRemotePort();
            
            
            nom = request.getParameter("nombre");
            appat = request.getParameter("appat");
            apmat = request.getParameter("apmat");
            edad = Integer.parseInt(request.getParameter("edad"));
            correo = request.getParameter("correo");
            
            
            try {
                
                // query para poder insertar los datos en la base de datos
                /*
                    insert into nombretabla (atributo1, atributo2, ...)
                    value (valor1, valor2, ...)
                */
                
                String q = "insert into mregistro (nom_usu, appat_usu, "
                        + "apmat_usu, edad_usu, email_usu)"
                        + "values ('"+nom+"','"+appat+"','"+apmat+"',"+edad+",'"+correo+"')";
                
                //inserta los datos
                set.executeUpdate(q);
                
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Registro</title>"
                        + "<link rel=\"stylesheet\" href=\"css/style.css\">");            
                out.println("</head>");
                out.println("<body class='contenedor-Actualizar'>"
                        + "<div>");
                out.println("<h1>Registro Exitoso</h1>"

                        + "Tu nombre es: "+nom
                        + "<br>"
                        + "Tu apellido paterno es: "+appat
                        + "<br>"
                        + "Tu apellido materno es: "+apmat
                        + "<br>"
                        + "Tu edad es: "+edad
                        + "<br>"
                        + "Tu correo es: "+correo
                        + "<br>"
                        + "La IP Local es: "+ip
                        + "<br>"
                        + "La IP del host: "+iph
                        + "<br>"
                        + "Puerto Local: " + puerto
                        + "<br>"
                        + "Puerto Host:" + puertoh
                        + "<br>"
                        + "<a href='index.html'>Regresar al formulario</a>"
                        + "<br>"
                        + "<a href='Consultar'>Consultar la Tabla General de Usuarios</a>"
                                + "</div>");
                out.println("</body>");
                out.println("</html>");
                
                System.out.println("Datos registrados en la tabla");
            } catch (Exception e) {
            
                System.out.println("No se registraron los datos en la tabla");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Registro</title>"
                        + "<link rel=\"stylesheet\" href=\"css/style.css\">");            
                out.println("</head>");
                out.println("<body class='contenedor-Eliminar'>"
                        + "<div>"
                        + "<h1>No se pudo registrar, hubo un error</h1>"
                + "<a href='index.html'>Regresar al formulario</a>"
                + "<br>"
                + "<a href='Consultar'>Consultar la Tabla General de Usuarios</a>"
                        + "</div>");
                out.println("</body>");
                out.println("</html>");
            }  
        }
    }
    
    public void destroy(){
        try {
            con.close();
        } catch (Exception e) {
            super.destroy();
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
