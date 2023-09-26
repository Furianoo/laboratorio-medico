package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import java.util.HashMap;
import java.util.Map;

public class TelaPrincipal extends JFrame {
    private Map<Class<? extends JFrame>, JFrame> openedFrames = new HashMap<>();

    public TelaPrincipal() {
        setTitle("Sistema de Gerenciamento Médico");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        addButton(buttonPanel, "Cadastrar Paciente", CadastroPacienteForm.class, "icons/paciente.png");
        addButton(buttonPanel, "Cadastrar Médico", CadastroMedicoForm.class, "icons/medico.png");
        addButton(buttonPanel, "Cadastrar Exame", CadastroExameForm.class, "icons/exame.png");
        addButton(buttonPanel, "Agendar Exame", AgendamentoExameForm.class, "icons/agenda.png");
        addButton(buttonPanel, "Consultar Todos os Pacientes", ConsultaPacientesForm.class, "icons/pesquisar.png");
        addButton(buttonPanel, "Consultar Pacientes Aniversariantes do Mês", PacientesAniversariantesForm.class, "icons/aniversario.png");
        addButton(buttonPanel, "Consultar Exames por Tipo", ConsultaExamesPorTipoForm.class, "icons/tipo.png");
        addButton(buttonPanel, "Consultar Exames Agendados", ConsultaExamesAgendadosForm.class, "icons/agenda.png");
        addButton(buttonPanel, "Consultar Exames Realizados", ConsultaExamesRealizadosForm.class, "icons/realizados.png");
        addButton(buttonPanel, "Consultar Exames por Paciente", ConsultaExamesPorPacienteForm.class, "icons/pesquisar.png");
        addButton(buttonPanel, "Sair", null, "icons/sair.png");

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void addButton(JPanel panel, String buttonText, final Class<? extends JFrame> frameClass, String iconPath) {
        JButton button = new JButton(buttonText, new ImageIcon(iconPath));
        styleButton(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frameClass != null) {
                    if (openedFrames.containsKey(frameClass)) {
                        JFrame existingFrame = openedFrames.get(frameClass);
                        existingFrame.toFront();
                    } else {
                        try {
                            JFrame newFrame = frameClass.getDeclaredConstructor().newInstance();
                            openedFrames.put(frameClass, newFrame);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        });

        panel.add(button);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.blue);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(800, 70)); // Expande para a largura da tela
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal();
            }
        });
    }
}