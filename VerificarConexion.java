import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class VerificarConexion {
    public static void main(String[] args) {
        // Datos de conexión
        String serverName = "localhost";
        String databaseName = "proyectoBD"; // aqui el nombre de BD
        String username = "sa"; // Usuario por defecto
        String password = "1234"; // aqui pones tu contraseña

        try {
            // Cargar el controlador JDBC de SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Configurar las propiedades de conexión
            Properties connectionProps = new Properties();
            connectionProps.setProperty("user", username);
            connectionProps.setProperty("password", password);
            connectionProps.setProperty("encrypt", "true");
            connectionProps.setProperty("trustServerCertificate", "true");

            // Establecer la conexión
            Connection connection = DriverManager.getConnection(
                "jdbc:sqlserver://" + serverName + ";databaseName=" + databaseName,
                connectionProps
            );

            System.out.println("Conectado a la base de datos SQL Server");

            // Aquí puedes ejecutar tus consultas SQL
            // ...

            // Cerrar la conexión
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos: " + e.getMessage());
        }
    }
}