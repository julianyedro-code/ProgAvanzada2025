package ui;

import auth.IAuthService;
import menu.IMenuService;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;

    private final IAuthService authService;
    private final IMenuService menuService;

    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;

    public Login(IAuthService authService, IMenuService menuService) {
        super("Sistema - Login");
        this.authService = authService;
        this.menuService = menuService;
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(380, 220);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel content = new JPanel();
        content.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        content.setLayout(new BorderLayout(10, 10));
        setContentPane(content);

        JLabel lblTitle = new JLabel("Iniciar sesión", SwingConstants.CENTER);
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 18f));
        content.add(lblTitle, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        content.add(form, BorderLayout.CENTER);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0; c.gridy = 0;
        form.add(new JLabel("Usuario:"), c);

        c.gridx = 1; c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 1.0;
        txtUser = new JTextField();
        form.add(txtUser, c);

        c.gridx = 0; c.gridy = 1; c.fill = GridBagConstraints.NONE; c.weightx = 0;
        form.add(new JLabel("Contraseña:"), c);

        c.gridx = 1; c.fill = GridBagConstraints.HORIZONTAL; c.weightx = 1.0;
        txtPass = new JPasswordField();
        form.add(txtPass, c);

        btnLogin = new JButton("Ingresar");
        btnLogin.addActionListener(e -> doLogin());

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(btnLogin);
        content.add(actions, BorderLayout.SOUTH);

        KeyAdapter enterKey = new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) doLogin();
            }
        };
        txtUser.addKeyListener(enterKey);
        txtPass.addKeyListener(enterKey);
    }

    private void doLogin() {
        try {
            String username = txtUser.getText().trim();
            String password = new String(txtPass.getPassword());

            User logged = authService.login(username, password);
            if (logged == null) {
                JOptionPane.showMessageDialog(this, "Credenciales inválidas",
                        "Error", JOptionPane.ERROR_MESSAGE);
                txtPass.setText("");
                txtUser.requestFocus();
                return;
            }

            SwingUtilities.invokeLater(() -> {
                MainMenu menu = new MainMenu(logged, this, menuService);
                menu.setVisible(true);
                this.setVisible(false);
            });
        } catch (HeadlessException | IllegalStateException ex) { // multi-catch
            JOptionPane.showMessageDialog(this, "Ocurrió un problema en la UI: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}