/*
 * KeyVaultClient
 * The key vault client performs cryptographic key operations and vault operations against the Key Vault service.
 *
 * OpenAPI spec version: 2016-10-01
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.microsoft.identity.internal.test.keyvault.model;

import com.google.gson.annotations.SerializedName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * The contact information for the vault certificates.
 */
@ApiModel(description = "The contact information for the vault certificates.")
public class Contact {
    @SerializedName("email")
    private String email = null;

    @SerializedName("name")
    private String name = null;

    @SerializedName("phone")
    private String phone = null;

    public Contact email(String email) {
        this.email = email;
        return this;
    }

    /**
     * Email addresss.
     * @return email
     **/
    @ApiModelProperty(value = "Email addresss.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contact name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Name.
     * @return name
     **/
    @ApiModelProperty(value = "Name.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact phone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Phone number.
     * @return phone
     **/
    @ApiModelProperty(value = "Phone number.")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return Objects.equals(this.email, contact.email)
                && Objects.equals(this.name, contact.name)
                && Objects.equals(this.phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, phone);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Contact {\n");

        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
