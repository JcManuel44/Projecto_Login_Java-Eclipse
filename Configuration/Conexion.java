package Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

    private static final String USUARIO = "user_control_clientes";
    private static final String CONTRASENA = "123";
    private static final String URL = "jdbc:sqlserver://localhost:1433;encrypt=false;databaseName=_1_Java_Login";


    public static PreparedStatement getPreparedStatement(Connection conexion, String sql) throws SQLException {
        return conexion.prepareStatement(sql);
    }

    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexión establecida con éxito.");
            return conexion;
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador JDBC:");
            e.printStackTrace();
            throw new SQLException("Error al cargar el controlador JDBC", e);
        } catch (SQLException e) {
            System.out.println("Error al obtener la conexión:");
            e.printStackTrace();
            throw e;
        }
    }

    public static void cerrarRecursosConsulta(Connection conexion, PreparedStatement preparedStatement, ResultSet resultSet) {
        if (conexion != null) {
            cerrarResultSet(resultSet);
            cerrarPreparedStatement(preparedStatement);
            cerrarConexion(conexion);
        }
    }

    public static void cerrarRecursoTransaccion(Connection conexion, PreparedStatement preparedStatement) {
        if (conexion != null) {
            cerrarPreparedStatement(preparedStatement);
            cerrarConexion(conexion);
        }
    }

    public static void cerrarResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
                System.out.println("ResultSet cerrado con éxito.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar el ResultSet:");
                e.printStackTrace();
            }
        }
    }

    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada con éxito.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión:");
                e.printStackTrace();
            }
        }
    }

    public static void cerrarPreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
                System.out.println("PreparedStatement cerrado con éxito.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar el PreparedStatement:");
                e.printStackTrace();
            }
        }
    }
    
  /*  public static void main(String[] args) {
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            conexion = Conexion.obtenerConexion();

            String sql = "SELECT * FROM Usuario";
            preparedStatement = conexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Columna1: " + resultSet.getString("id"));
                System.out.println("Columna2: " + resultSet.getString("nombre_usuario"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarRecursosConsulta(conexion, preparedStatement, resultSet);
        }
    }
    */
}

