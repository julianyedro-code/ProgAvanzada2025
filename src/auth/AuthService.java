package auth;

import java.util.HashMap;
import java.util.Map;

import model.Role;
import model.User;

public class AuthService implements IAuthService {

    private static AuthService INSTANCE;
    public static synchronized AuthService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AuthService();
        }
        return INSTANCE;
    }

    private final Map<String, User> users = new HashMap<>();

    private AuthService() {
        users.put("regional", new User("regional", "1234", "Ana Regional", Role.GERENTE_REGIONAL));
        users.put("gerente",  new User("gerente",  "1234", "Bruno Gerente", Role.GERENTE));
        users.put("jefe",     new User("jefe",     "1234", "Carla Jefe", Role.JEFE));
        users.put("operario", new User("operario", "1234", "Diego Operario", Role.OPERARIO));
    }

    @Override
    public User login(String username, String password) {
        User u = users.get(username);
        return (u != null && u.passwordMatches(password)) ? u : null;
    }
}
