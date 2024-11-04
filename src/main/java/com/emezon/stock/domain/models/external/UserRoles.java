package com.emezon.stock.domain.models.external;

public enum UserRoles {
    AUX_BODEGA("aux_bodega"),
    ADMIN("admin"),
    CLIENT("client");

    private final String name;

    UserRoles(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static final String[] ADD_SUPPLY_ROLES = { UserRoles.ADMIN.toString(), UserRoles.AUX_BODEGA.toString() };

}
