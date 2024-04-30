package Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Empleado;
import Configuration.Conexion;

public class DaoEmpleado {
    
    private Empleado empleado = null;
    private Connection conexion = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    private static final String INSERT_EMPLEADO = "EXEC SP_IngresarEmpleado ?,?,?,?,?,?";
    
    public boolean insertarEmpleado(Empleado empleado) throws SQLException {
        CallableStatement callableStatement = null;
        boolean insercionExitosa = false;

        try {
            conexion = Conexion.obtenerConexion();
            callableStatement = conexion.prepareCall(INSERT_EMPLEADO);
            callableStatement.setString(1, empleado.getNombre());
            callableStatement.setString(2, empleado.getApellido());
            callableStatement.setString(3, String.valueOf(empleado.getSexo()));
            callableStatement.setInt(4, empleado.getTelefono());
            callableStatement.setDate(5, empleado.getFecha());
            callableStatement.setInt(6, empleado.getUsuario_id());

            int filasInsertadas = callableStatement.executeUpdate();
            insercionExitosa = filasInsertadas > 0;
        } finally {
            Conexion.cerrarRecursoTransaccion(conexion, callableStatement);
        }

        return insercionExitosa;
    }

}
