package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import Dao.DaoUsuario;
import Model.Cargo;
import Model.Roles;
import Model.Usuario;

/**
 * Servlet implementation class Servlet_Login
 */
@WebServlet("/Servlet_Login")
public class Servlet_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet_Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").serializeNulls().create();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String message = "";

		if ("LOGIN".equals(action)) {
			String usuario = request.getParameter("usuario");
			String password = request.getParameter("password");

			try {
				DaoUsuario daoUsuario = new DaoUsuario(); // Instancia del DaoUsuario
				Usuario loggedInUser = daoUsuario.iniciarSesion(usuario, password);

				// Dentro del bloque "if (loggedInUser != null) {"
				if (loggedInUser != null) {
				    // Establecer los atributos de sesión
				    session.setAttribute("loggedInUser", loggedInUser);
				    session.setAttribute("isLoggedIn", true);

				    // Determinar qué página redirigir según el rol del usuario
				    String nextPage = redireccionamientoPorRol(loggedInUser);

				    // Crear un objeto JSON para enviar la respuesta al cliente
				    JsonObject jsonResponse = new JsonObject();
				    jsonResponse.addProperty("success", true);
				    jsonResponse.addProperty("redirectUrl", nextPage);

				    // Enviar la respuesta JSON al cliente
				    out.print(jsonResponse.toString());
				    out.flush();
				} else {
				    session.setAttribute("isLoggedIn", false); // Establecer la bandera de inicio de sesión como falsa
				                                                // en caso de error de inicio de sesión

				    // Enviar la respuesta de fallo al cliente
				    JsonObject jsonResponse = new JsonObject();
				    jsonResponse.addProperty("success", false);

				    out.print(jsonResponse.toString());
				    out.flush();
				}

			} catch (SQLException e) {
				message = "error";
				e.printStackTrace();
			}
		}
	}
	

	private String redireccionamientoPorRol(Usuario usuario) {
	    String nombreCargo = usuario.getCargo().getNombre_cargo(); // Obtener el nombre del cargo

	    // Convertir el nombre del cargo a un valor de la enumeración Roles
	    Roles role = Roles.valueOf(nombreCargo);


	    // Determinar qué página redirigir según el rol del usuario
	    switch (role) {
	        case Administrador:
	            return "Administrador.jsp";
	        case Usuario:
	            return "Usuario.jsp";
	        default:
	            return "Login.jsp";
	    }
	}




}
