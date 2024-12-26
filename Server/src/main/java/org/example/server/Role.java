package org.example.server;

public enum Role {
    USER(false),
    ADMIN(true);
    private boolean isAdmin;

    Role(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }
}
