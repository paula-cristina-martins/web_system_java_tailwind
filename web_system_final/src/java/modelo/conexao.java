package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class conexao {

    public Connection iniciarConexao() throws SQLException, ClassNotFoundException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/system_web";
        String dbUser = "root";
        String dbPassword = "admin";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
    }

    public void fecharConexao(Connection connection, PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}
