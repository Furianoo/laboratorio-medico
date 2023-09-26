package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TelaLogin extends JFrame {
    private JTextField usuarioField;
    private JPasswordField senhaField;

    private Map<String, String> usuarios = new HashMap<>(); // Simulação de dados de usuários e senhas

    public TelaLogin() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Simulação de dados de usuários e senhas
        usuarios.put("usuario1", "senha1");
        usuarios.put("usuario2", "senha2");

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new GridLayout(3, 2));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        contentPanel.add(new JLabel("Usuário:"));
        usuarioField = new JTextField();
        contentPanel.add(usuarioField);

        contentPanel.add(new JLabel("Senha:"));
        senhaField = new JPasswordField();
        contentPanel.add(senhaField);

        container.add(contentPanel, BorderLayout.CENTER);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText();
                char[] senhaChars = senhaField.getPassword();
                String senha = new String(senhaChars);

                if (autenticarUsuario(usuario, senha)) {
                    JOptionPane.showMessageDialog(TelaLogin.this, "Login bem-sucedido!");
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            new TelaPrincipal();
                        }
                    });
                } else {
                    JOptionPane.showMessageDialog(TelaLogin.this, "Usuário ou senha incorretos.");
                    setVisible(true);
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(loginButton);
        container.add(buttonPanel, BorderLayout.SOUTH);

        // Carregando o estilo do arquivo CSS
        String cssFilePath = new File("style.css").toURI().toString();
        UIManager.getDefaults().put("TextFieldUI", "javax.swing.plaf.basic.BasicTextFieldUI");
        UIManager.put("TextField.border", BorderFactory.createEmptyBorder());

        String style = "<link rel='stylesheet' type='text/css' href='" + cssFilePath + "'>";
        UIManager.put("OptionPane.messageStyleSheet", style);

        setVisible(true);
    }

    private boolean autenticarUsuario(String usuario, String senha) {
        setVisible(false);
        return usuario.equals("admin") && senha.equals("senha");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin();
            }
        });
    }
}