package Factory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static String status = "Nao Conectou";

    // MÃ©todo de ConexÃ£o
    public static Connection getConnection() {
        Connection connection;
        // Altere essa string pelo seu username do mysql
        String username = "root";

        // Altere essa string pela sua senha do mysql
        String password = "HereGoesYourPassword";
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/erp_supermercado?useTimezone=true&serverTimezone=UTC", username, password);
            //Testa sua conexao
            if (c != null) {
                status = ("STATUS--->Conectado com sucesso!");
            }else {
                status = ("STATUS--->Não foi possivel realizar conexão");
            }
            return c;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo que retorna o status da sua conexÃ£o
    public static String statusConnection() {
        return status;
    }

    // Metodo que fecha sua conexÃ£o
    public static void closeConnection() {
        try {
            ConnectionFactory.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo que reinicia sua conexÃ£o
    public static Connection resetConnection() {
        closeConnection();
        return ConnectionFactory.getConnection();
    }
}
