package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.DaoEmpleado;
import Dao.DaoUsuario;
import Model.Empleado;
import Model.Usuario;

/**
 * Servlet implementation class Servlet_Usuario
 */
@WebServlet("/Servlet_Usuario")
public class Servlet_Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_Usuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		  System.out.print("Accion: "+action);
		
		System.out.println("accion"+action);
        if (action != null && !action.isEmpty()) {
            if (action.equals("guardar")) {
                // Recibir los datos del formulario para insertar un usuario
                String nombreUsuario = request.getParameter("nombreUsuario");
                String claveUsuario = request.getParameter("claveUsuario");
                String cargoUsuario = request.getParameter("cargoUsuario");
                
                System.out.print("Nombre-Usuario: "+nombreUsuario);
                System.out.print("Clave-Usuario: "+claveUsuario);
                System.out.print("Cargo-Usuario: "+cargoUsuario);

                // Crear un objeto Usuario y establecer los datos utilizando setters
                Usuario usuario = new Usuario();
                usuario.setNombre(nombreUsuario);
                usuario.setClave(claveUsuario);
                usuario.setCargo_id(Integer.parseInt(cargoUsuario));


                // Llamar al método insertarUsuario del DAO
                DaoUsuario daoUsuario = new DaoUsuario();
                int ultimoIdInsertado = 0;
                try {
                	ultimoIdInsertado = daoUsuario.insertarUsuario(usuario);

                } catch (SQLException e) {
                    // Manejar errores de SQL
                    e.printStackTrace();
                    response.getWriter().println("Error al insertar usuario: " + e.getMessage());
                }
   
                // Recibir los datos del formulario para insertar un empleado
                String nombreEmpleado = request.getParameter("nombreEmpleado");
                String apellidoEmpleado = request.getParameter("apellidoEmpleado");
                char sexoEmpleado = request.getParameter("sexoEmpleado").charAt(0);
                String telefono = request.getParameter("telefonoEmpleado");
                String fechaString = request.getParameter("fechaNacimientoEmpleado");
                
                System.out.print("Nombre-Empleado: "+nombreEmpleado);
                System.out.print("Apellido-Empleado: "+apellidoEmpleado);
                System.out.print("Sexo-Empleado: "+sexoEmpleado);
                System.out.print("telefono: "+telefono);
                System.out.print("fecha: "+fechaString);
               
                
     
             

                // Crear un objeto Empleado y establecer los datos utilizando setters
                Empleado empleado = new Empleado();
                empleado.setNombre(nombreEmpleado);
                empleado.setApellido(apellidoEmpleado);
                empleado.setSexo(sexoEmpleado);
                empleado.setTelefono(Integer.parseInt(telefono));
               
                // Fecha
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilFecha = null;
				try {
					utilFecha = sdf.parse(fechaString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                empleado.setFecha(new java.sql.Date(utilFecha.getTime()));
                empleado.setSexo(sexoEmpleado);
                
                empleado.setUsuario_id(ultimoIdInsertado);
                
                // Llamar al método insertarEmpleado del DAO
                DaoEmpleado daoEmpleado = new DaoEmpleado();
                try {
                    boolean insercionExitosa = daoEmpleado.insertarEmpleado(empleado);
                    
                    if (ultimoIdInsertado > -1) {
                    	 if (insercionExitosa) {
                             response.getWriter().println("Empleado insertado correctamente");
                             System.out.println("---> EMPLEADO INSERTADO <---");
                         } else {
                             response.getWriter().println("Error al insertar empleado");
                         }
                    } else {
                        response.getWriter().println("Error al insertar usuario");
                    }
                   
                } catch (SQLException e) {
                    // Manejar errores de SQL
                    e.printStackTrace();
                    response.getWriter().println("Error al insertar empleado: " + e.getMessage());
                }
            } else {
                response.getWriter().println("Acción desconocida");
            }
        } else {
            response.getWriter().println("No se especificó ninguna acción");
        }
   
	    }

}
