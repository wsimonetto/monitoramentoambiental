package br.com.fiap.monitoramentoambiental.model;

public enum UsuarioRole {

    ADMIN("admin"),
    USER("user");

    private String role;

    UsuarioRole(String role) {
        this.role = role;
    }

} // FIM
