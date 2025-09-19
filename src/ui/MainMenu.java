package ui;

import menu.IMenuService;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;

    private final User user;
    private final JFrame loginFrame;
    private final IMenuService menuService;

    private JPanel optionsPanel;
    private JLabel lblUser;

    public MainMenu(User user, JFrame loginFrame, IMenuService menuService) {
        super("Sistema - Menú Principal");
        this.user = user;
        this.loginFrame = loginFrame;
        this.menuService = menuService;
        initUI();
        renderMenuOptions();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 420);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(480, 360));

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        setContentPane(content);

        JPanel header = new JPanel(new BorderLayout());
        JLabel lblTitle = new JLabel("Menú Principal");
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 20f));
        header.add(lblTitle, BorderLayout.WEST);

        lblUser = new JLabel();
        lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
        header.add(lblUser, BorderLayout.EAST);
        content.add(header, BorderLayout.NORTH);

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(optionsPanel);
        content.add(scroll, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnChangePass = new JButton("Cambiar contraseña");
        btnChangePass.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "[TODO] Cambiar contraseña"));
        JButton btnLogout = new JButton("Cerrar sesión");
        btnLogout.addActionListener(e -> {
            this.dispose();
            if (loginFrame != null) loginFrame.setVisible(true);
        });
        JButton btnExit = new JButton("Salir");
        btnExit.addActionListener(e -> System.exit(0));
        footer.add(btnChangePass);
        footer.add(btnLogout);
        footer.add(btnExit);
        content.add(footer, BorderLayout.SOUTH);
    }

    private void renderMenuOptions() {
        lblUser.setText(user.getNombre() + " — Rol: " + user.getRole());
        List<String> options = menuService.optionsFor(user.getRole());

        optionsPanel.removeAll();
        for (int i = 0; i < options.size(); i++) {
            final String text = (i + 1) + ") " + options.get(i);
            JButton btn = new JButton(text);
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            btn.addActionListener(e ->
                    JOptionPane.showMessageDialog(this, "[TODO] " + text));
            JPanel row = new JPanel(new BorderLayout());
            row.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
            row.add(btn, BorderLayout.CENTER);
            optionsPanel.add(row);
        }
        optionsPanel.revalidate();
        optionsPanel.repaint();
    }
}
