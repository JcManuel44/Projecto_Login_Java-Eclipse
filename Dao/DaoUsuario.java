package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import Model.Cargo;
import Model.Roles;
import Model.Usuario;
import Configuration.Conexion;

public class DaoUsuario {


	private Usuario usuario = null;
	private Connection conexion = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private static final String SP_LOGIN = "EXEC SP_IniciarSesion ?,?";
	private static final String INSERT_USUARIO = "EXEC SP_IngresarUsuario ?,?,?,?";
	
	private Usuario mapResultSetToUsuario(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		String nombre = resultSet.getString("nombre_usuario");
		boolean estado = resultSet.getBoolean("estado");
		int cargoId = resultSet.getInt("cargo_id");
		String nombreCargo = resultSet.getString("nombre_cargo");
		Cargo cargo = new Cargo(cargoId, nombreCargo);

		return new Usuario(id, nombre, estado, cargo); // Dejamos la contraseña en blanco por razones de seguridad
	}


	public Usuario iniciarSesion(String usuario, String password) throws SQLException {
		Usuario loggedInUser = null;
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		try {
			con = Conexion.obtenerConexion();
			cs = con.prepareCall(SP_LOGIN);
			cs.setString(1, usuario);
			cs.setString(2, password);
			rs = cs.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String nombreUsuario = rs.getString("nombre_usuario");

				// Verificar si el conjunto de resultados contiene valores válidos
				if (id != 0 && nombreUsuario != null && !nombreUsuario.isEmpty()) {
					loggedInUser = mapResultSetToUsuario(rs);
					System.out.print("1 ->  " + rs + " <-");
					System.out.println("id: " + id + " nombre: " + nombreUsuario);
				} else {
					System.out.print("El usuario no existe");
				}
			}

		} catch (SQLException ex) {
			System.err.println("Error de SQL al intentar iniciar sesión: " + ex.toString());
			throw ex;
		} finally {
			Conexion.cerrarRecursosConsulta(con, cs, rs);
		}

		return loggedInUser;
	}

	public int insertarUsuario(Usuario usuario) throws SQLException {
		Connection conexion = null;
		CallableStatement callableStatement = null;
		int ultimoIdInsertado = -1;

		try {
			conexion = Conexion.obtenerConexion();
			callableStatement = conexion.prepareCall(INSERT_USUARIO);
			callableStatement.setString(1, usuario.getNombre());
			callableStatement.setInt(2, usuario.getCargo_id());
			callableStatement.setString(3, usuario.getClave()); // asumiendo que usuario.getClave() devuelve un arreglo
																// de bytes
			callableStatement.registerOutParameter(4, Types.INTEGER); // Parámetro de salida para el último ID insertado

			callableStatement.executeUpdate();

			// Recuperar el valor del parámetro de salida
			ultimoIdInsertado = callableStatement.getInt(4);
			System.out.println("Último ID insertado: " + ultimoIdInsertado);
		} finally {
			Conexion.cerrarRecursoTransaccion(conexion, callableStatement);
		}

		return ultimoIdInsertado;
	}

}
