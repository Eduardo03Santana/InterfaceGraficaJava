package DAO;
import Factory.ConnectionFactory;
import Model.Cliente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private final Connection connection;


    public ClienteDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void inserirCliente(Cliente cliente) {
        String sql = "INSERT INTO tbl_cliente(nome, cpf) VALUES(?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public void atualizarCliente(Cliente cliente) {
        try {
            PreparedStatement stmt = connection.prepareStatement("UPDATE tbl_cliente SET nome = ?, cpf = ? WHERE id = ?");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setInt(3, cliente.getId());
            stmt.executeUpdate();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public void deletarCliente(Cliente cliente) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM tbl_cliente WHERE id = ?");
            stmt.setInt(1, cliente.getId());
            stmt.executeUpdate();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection();

        }
    }


    public static List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        Cliente obj;
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tbl_cliente ORDER BY nome");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                obj = new Cliente();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection();
        }
        return lista;
    }
}



