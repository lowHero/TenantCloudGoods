package com.nz2dev.tenantcloudgoods.domain.models;

import java.io.Serializable;

/**
 * Created by nz2Dev on 24.03.2018
 */
public class User implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (admin != user.admin) return false;
        return externalId != null ? externalId.equals(user.externalId) : user.externalId == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (externalId != null ? externalId.hashCode() : 0);
        result = 31 * result + (admin ? 1 : 0);
        return result;
    }

}
