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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The deleted certificate item containing metadata about the deleted certificate.
 */
@ApiModel(
        description =
                "The deleted certificate item containing metadata about the deleted certificate.")
public class DeletedCertificateItem {
    @SerializedName("id")
    private String id = null;

    @SerializedName("attributes")
    private CertificateAttributes attributes = null;

    @SerializedName("tags")
    private Map<String, String> tags = null;

    @SerializedName("x5t")
    private String x5t = null;

    public DeletedCertificateItem id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Certificate identifier.
     * @return id
     **/
    @ApiModelProperty(value = "Certificate identifier.")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeletedCertificateItem attributes(CertificateAttributes attributes) {
        this.attributes = attributes;
        return this;
    }

    /**
     * The certificate management attributes.
     * @return attributes
     **/
    @ApiModelProperty(value = "The certificate management attributes.")
    public CertificateAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(CertificateAttributes attributes) {
        this.attributes = attributes;
    }

    public DeletedCertificateItem tags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    public DeletedCertificateItem putTagsItem(String key, String tagsItem) {
        if (this.tags == null) {
            this.tags = new HashMap<String, String>();
        }
        this.tags.put(key, tagsItem);
        return this;
    }

    /**
     * Application specific metadata in the form of key-value pairs.
     * @return tags
     **/
    @ApiModelProperty(value = "Application specific metadata in the form of key-value pairs.")
    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public DeletedCertificateItem x5t(String x5t) {
        this.x5t = x5t;
        return this;
    }

    /**
     * Thumbprint of the certificate.
     * @return x5t
     **/
    @ApiModelProperty(value = "Thumbprint of the certificate.")
    public String getX5t() {
        return x5t;
    }

    public void setX5t(String x5t) {
        this.x5t = x5t;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeletedCertificateItem deletedCertificateItem = (DeletedCertificateItem) o;
        return Objects.equals(this.id, deletedCertificateItem.id)
                && Objects.equals(this.attributes, deletedCertificateItem.attributes)
                && Objects.equals(this.tags, deletedCertificateItem.tags)
                && Objects.equals(this.x5t, deletedCertificateItem.x5t);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attributes, tags, x5t);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DeletedCertificateItem {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
        sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
        sb.append("    x5t: ").append(toIndentedString(x5t)).append("\n");
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
