/*
 * Azure Identity Labs API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.model.CustomErrorResponse;
import io.swagger.client.model.CustomSuccessResponse;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for LabPolicyInfoApi
 */
@Ignore
public class LabPolicyInfoApiTest {

    private final LabPolicyInfoApi api = new LabPolicyInfoApi();

    /**
     * Will give you the different Policies available to be used with Create Temp User / Enable/Disable Policy API
     *
     * Policy will be listed as GlobalMFA, MAMCA, MDMCA, MFAONSPO, MFAONEXO. Use the LabUserInfo endpoint to query the user policy info.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void apiLabPolicyInfoGetTest() throws ApiException {
        CustomSuccessResponse response = api.apiLabPolicyInfoGet();

        // TODO: test validations
    }
}
