package com.nz2dev.tenantcloudgoods.domain.models;

/**
 * Created by nz2Dev on 24.03.2018
 */
public class User {

    public static User createCustomer(String externalId) {
        return new User(0, externalId, false);
    }

    private int id;
    private String externalId;
    private boolean admin;

    public User(int id, String externalId, boolean admin) {
        this.id = id;
        this.externalId = externalId;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public String getExternalId() {
        return externalId;
    }

    public boolean isAdmin() {
        return admin;
    }
}
