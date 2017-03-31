package com.spotlight.model.oauth;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author IliaNik on 15.03.2017.
 */
@Entity
@Table(name = "oauth_code")
public class OauthCodeEntity {

    @Id
    private String code;

    private byte[] authentication;

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "authentication")
    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OauthCodeEntity that = (OauthCodeEntity) o;
        return Objects.equals(code, that.code) &&
                Arrays.equals(authentication, that.authentication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, authentication);
    }
}
