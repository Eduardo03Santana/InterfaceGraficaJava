package GUI;

import DAO.ClienteDAO;
import Model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;

public class Cadastro {
    public Cadastro() {
    }
    // Instanciando Objetos
    List<Cliente> clientList = ClienteDAO.listarClientes();
    private int firstJCombo = 0;
    public void puxarDados(){
    
    }
    public void criarTela() {
        //Definindo Tela
        JFrame tela = new JFrame("Cadastro");
        //Definindo Input's e labels de usuario

        //Nome
        JLabel nomeUserLabel = new JLabel("Nome: ");
        JTextField nomeUserInput = new JTextField(10);

        //CPF
        JLabel cpfUserLabel = new JLabel("CPF: ");
        JTextField cpfUserInput = new JTextField(10);

        //Definindo Caixa de Opcoes
        JComboBox<String> usuarios = new JComboBox<>();
        usuarios.addItem("Selecionar Cliente");
        usuarios.addItem("Novo Cliente");
        for (Cliente value : clientList) {
            usuarios.addItem(value.getNome());
        }
        //Definindo JLabel para mostrar usuario selecionado || Apenas para testes iniciais.
        JLabel selectedUser = new JLabel(" ");

        //Definindo Botoes de Limpar e Cadastrar
        JButton clearButton = new JButton("Limpar Campos");
        JButton registerButton = new JButton("Guardar Dados");
        JButton dataGetButton = new JButton("Puxar Dados");
        JButton deleteButton = new JButton("Apagar Usuário");

        //Container
        Container painelConteudo = tela.getContentPane();
        painelConteudo.add(usuarios);
        painelConteudo.add(selectedUser);
        painelConteudo.setLayout(new GridLayout(0, 2));

        //Nome
        painelConteudo.add(nomeUserLabel);
        painelConteudo.add(nomeUserInput);

        //CPF
        painelConteudo.add(cpfUserLabel);
        painelConteudo.add(cpfUserInput);

        //Botoes
        painelConteudo.add(registerButton);
        painelConteudo.add(dataGetButton);
        painelConteudo.add(clearButton);
        painelConteudo.add(deleteButton);

        //Listeners ________________

        //Caixa de Opcoes
        usuarios.addActionListener((e) -> {
            //Limpar campos de texto ao trocar cliente
            nomeUserInput.setText("");
            cpfUserInput.setText("");

            if (firstJCombo == 0) {
                usuarios.removeItemAt(0);
                firstJCombo += 1;
            }
        });


        // Botao Guardar dados
        registerButton.addActionListener((ActionEvent e) -> {
            // Criar novo cliente
            ClienteDAO dao = new ClienteDAO();
            if (nomeUserInput.getText().trim().isEmpty() || cpfUserInput.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Os campos não podem ser vazios", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (Objects.requireNonNull(usuarios.getSelectedItem().toString().equals("Novo Cliente"))) {
                Cliente cliente = new Cliente(nomeUserInput.getText(), cpfUserInput.getText());
                clientList.add(cliente);
                usuarios.addItem(nomeUserInput.getText());
                dao.inserirCliente(cliente);
                clientList = ClienteDAO.listarClientes();
            }

            //Alterar dados de um cliente existente
            for (Cliente value : clientList) {
                if (value.getNome().equals(Objects.requireNonNull(usuarios.getSelectedItem()).toString())) {
                    if (!nomeUserInput.getText().trim().isEmpty() || !cpfUserInput.getText().trim().isEmpty()) {
                        String nomeChanged = nomeUserInput.getText();
                        value.setNome(nomeUserInput.getText());
                        value.setCpf(cpfUserInput.getText());
                        usuarios.removeItem(usuarios.getSelectedItem());
                        usuarios.addItem(nomeChanged);
                        Cliente cliente = new Cliente(value.getNome(), value.getCpf());
                        cliente.setId(value.getId());
                        dao.atualizarCliente(cliente);
                    }
                }
            }
        });


        // Botao Puxar dados
        dataGetButton.addActionListener((e) -> {
            for (Cliente cliente : clientList) {
                if (cliente.getNome().equals(Objects.requireNonNull(usuarios.getSelectedItem()).toString())) {
                    nomeUserInput.setText(cliente.getNome());
                    cpfUserInput.setText(cliente.getCpf());
                }
            }
        });

        // Botao Limpar Campos
        clearButton.addActionListener((e) -> {
            nomeUserInput.setText("");
            cpfUserInput.setText("");
        });

        // Botão Deletar Usuário
        deleteButton.addActionListener((e) -> {
            ClienteDAO dao = new ClienteDAO();
            for (Cliente value : clientList) {
                if (value.getNome().equals(Objects.requireNonNull(usuarios.getSelectedItem()).toString())) {
                    Cliente cliente = new Cliente(value.getNome(), value.getCpf());
                    cliente.setId(value.getId());
                    dao.deletarCliente(cliente);
                    usuarios.removeItem(usuarios.getSelectedItem());
                }
            }
        });

        //Setando visibilidade da tela e outras especificacoes
        {
            tela.pack();
            tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            tela.setLocationRelativeTo(null);
            tela.setVisible(true);
        }
    }
}