package com.nz2dev.tenantcloudgoods.domain.models;

import com.google.gson.Gson;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by nz2Dev on 24.03.2018
 */
public class UserTest {

    @Test
    public void toJson_fromJson_ShouldBeEquals() {
        final Gson gson = new Gson();
        final User user = new User(1, "asd", false);

        String userJson = gson.toJson(user);
        User userFromJson = gson.fromJson(userJson, User.class);

        assertThat(user).isEqualTo(userFromJson);
    }

}
