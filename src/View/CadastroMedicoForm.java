package View;

import Sevices.DB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroMedicoForm extends JFrame {
    private JTextField nomeField;
    private JTextField especialidadeField;
    private JTextField telefoneField;

    public CadastroMedicoForm() {
        setTitle("Cadastro de Médico");
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
        container.add(new JLabel("Especialidade:"), constraints);
        especialidadeField = new JTextField(20);
        constraints.gridx = 1;
        container.add(especialidadeField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        container.add(new JLabel("Telefone:"), constraints);
        telefoneField = new JTextField(20);
        constraints.gridx = 1;
        container.add(telefoneField, constraints);

        constraints.gridy++;
        constraints.gridx = 0;
        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarMedico();
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

    private void cadastrarMedico() {
        String nome = nomeField.getText();
        String especialidade = especialidadeField.getText();
        String telefone = telefoneField.getText();

        Connection conexao = DB.connecta();
        String sql = "INSERT INTO Medicos (Nome, Especialidade, Telefone) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, especialidade);
            preparedStatement.setString(3, telefone);

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Médico cadastrado com sucesso!");

            DB.desconecta(conexao);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar médico.");
        }
    }

    private void retornarTelaPrincipal() {
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CadastroMedicoForm();
            }
        });
    }
}