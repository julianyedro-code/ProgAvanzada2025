package model;

public class User {
    private final String username;
    private String password;
    private final String nombre;
    private final Role role;

    public User(String username, String password, String nombre, Role role) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getNombre() { return nombre; }
    public Role getRole() { return role; }

    public boolean passwordMatches(String raw) { return password.equals(raw); }

    public void changePassword(String newPass) { this.password = newPass; }
}