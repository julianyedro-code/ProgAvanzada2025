package auth;

import model.User;

public interface IAuthService {
    User login(String username, String password);
}
