package com.br.Agro_Mapping.model.enuns;

public enum UserRole {

    ADMIN("ADMIN"),
    COMMONDUSER("COMMONUSER"),
    SELLER("SELLER");

    private final String role;

    UserRole(String role) {
        this.role = role;
        }

        public String getRole() {
            return role;
        }
}
