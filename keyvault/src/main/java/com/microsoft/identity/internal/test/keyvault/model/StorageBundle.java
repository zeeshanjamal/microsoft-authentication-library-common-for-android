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

import java.util.Map;
import java.util.Objects;

/**
 * A Storage account bundle consists of key vault storage account details plus its attributes.
 */
@ApiModel(
        description =
                "A Storage account bundle consists of key vault storage account details plus its attributes.")
public class StorageBundle {
    @SerializedName("id")
    private String id = null;

    @SerializedName("resourceId")
    private String resourceId = null;

    @SerializedName("activeKeyName")
    private String activeKeyName = null;

    @SerializedName("autoRegenerateKey")
    private Boolean autoRegenerateKey = null;

    @SerializedName("regenerationPeriod")
    private String regenerationPeriod = null;

    @SerializedName("attributes")
    private StorageAccountAttributes attributes = null;

    @SerializedName("tags")
    private Map<String, String> tags = null;

    /**
     * The storage account id.
     * @return id
     **/
    @ApiModelProperty(value = "The storage account id.")
    public String getId() {
        return id;
    }

    /**
     * The storage account resource id.
     * @return resourceId
     **/
    @ApiModelProperty(value = "The storage account resource id.")
    public String getResourceId() {
        return resourceId;
    }

    /**
     * The current active storage account key name.
     * @return activeKeyName
     **/
    @ApiModelProperty(value = "The current active storage account key name.")
    public String getActiveKeyName() {
        return activeKeyName;
    }

    /**
     * whether keyvault should manage the storage account for the user.
     * @return autoRegenerateKey
     **/
    @ApiModelProperty(value = "whether keyvault should manage the storage account for the user.")
    public Boolean isAutoRegenerateKey() {
        return autoRegenerateKey;
    }

    /**
     * The key regeneration time duration specified in ISO-8601 format.
     * @return regenerationPeriod
     **/
    @ApiModelProperty(value = "The key regeneration time duration specified in ISO-8601 format.")
    public String getRegenerationPeriod() {
        return regenerationPeriod;
    }

    /**
     * The storage account attributes.
     * @return attributes
     **/
    @ApiModelProperty(value = "The storage account attributes.")
    public StorageAccountAttributes getAttributes() {
        return attributes;
    }

    /**
     * Application specific metadata in the form of key-value pairs
     * @return tags
     **/
    @ApiModelProperty(value = "Application specific metadata in the form of key-value pairs")
    public Map<String, String> getTags() {
        return tags;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StorageBundle storageBundle = (StorageBundle) o;
        return Objects.equals(this.id, storageBundle.id)
                && Objects.equals(this.resourceId, storageBundle.resourceId)
                && Objects.equals(this.activeKeyName, storageBundle.activeKeyName)
                && Objects.equals(this.autoRegenerateKey, storageBundle.autoRegenerateKey)
                && Objects.equals(this.regenerationPeriod, storageBundle.regenerationPeriod)
                && Objects.equals(this.attributes, storageBundle.attributes)
                && Objects.equals(this.tags, storageBundle.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                resourceId,
                activeKeyName,
                autoRegenerateKey,
                regenerationPeriod,
                attributes,
                tags);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StorageBundle {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
        sb.append("    activeKeyName: ").append(toIndentedString(activeKeyName)).append("\n");
        sb.append("    autoRegenerateKey: ")
                .append(toIndentedString(autoRegenerateKey))
                .append("\n");
        sb.append("    regenerationPeriod: ")
                .append(toIndentedString(regenerationPeriod))
                .append("\n");
        sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
        sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
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
