package View;

import Sevices.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class CadastroPacienteForm extends JFrame {
    private JTextField nomeField;
    private JTextField dataNascimentoField;
    private JTextField generoField;
    private JTextField telefoneField;
    private JTextField enderecoField;

    public CadastroPacienteForm() {
        setTitle("Cadastro de Paciente");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        container.add(new JLabel("Nome:"), constraints);
        nomeField = new JTextField(20);
        constraints.gridx = 1;
        container.add(nomeField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        container.add(new JLabel("Data de Nascimento (YYYY-MM-DD):"), constraints);
        dataNascimentoField = new JTextField(20);
        constraints.gridx = 1;
        container.add(dataNascimentoField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        container.add(new JLabel("Gênero:"), constraints);
        generoField = new JTextField(20);
        constraints.gridx = 1;
        container.add(generoField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        container.add(new JLabel("Telefone:"), constraints);
        telefoneField = new JTextField(20);
        constraints.gridx = 1;
        container.add(telefoneField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        container.add(new JLabel("Endereço:"), constraints);
        enderecoField = new JTextField(20);
        constraints.gridx = 1;
        container.add(enderecoField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarPaciente();
            }
        });
        container.add(cadastrarButton, constraints);

        constraints.gridx = 1;
        JButton voltarButton = new JButton("Voltar à Tela Principal");
        voltarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                retornarTelaPrincipal();
            }
        });
        container.add(voltarButton, constraints);

        add(container);

        pack();
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setVisible(true);
    }

    private void cadastrarPaciente() {
        String nome = nomeField.getText();
        String dataNascimentoStr = dataNascimentoField.getText();
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr);
        String genero = generoField.getText();
        String telefone = telefoneField.getText();
        String endereco = enderecoField.getText();

        Connection conexao = DB.connecta();
        try {
            String sql = "INSERT INTO Pacientes (Nome, DataNascimento, Genero, Telefone, Endereco) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            preparedStatement.setObject(2, dataNascimento);
            preparedStatement.setString(3, genero);
            preparedStatement.setString(4, telefone);
            preparedStatement.setString(5, endereco);

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Paciente cadastrado com sucesso!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar paciente.");
        } finally {
            if(conexao != null) {
                DB.desconecta(conexao);
            }
        }
    }

    private void retornarTelaPrincipal() {
//        TelaPrincipal telaPrincipal = new TelaPrincipal();
//        telaPrincipal.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroPacienteForm();
            }
        });
    }
}






