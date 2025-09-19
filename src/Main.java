import javax.swing.*;

import auth.AuthService;
import auth.IAuthService;
import menu.IMenuService;
import menu.RoleBasedMenuService;
import ui.Login;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
        	
            System.err.println("No se pudo aplicar Look&Feel: " + ex.getMessage());
        }

        // Singletons
        final IAuthService auth = AuthService.getInstance();
        final IMenuService menu = RoleBasedMenuService.getInstance();

        SwingUtilities.invokeLater(() -> {
            Login frame = new Login(auth, menu);
            frame.setVisible(true);
        });
    }
}
