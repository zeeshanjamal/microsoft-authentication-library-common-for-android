//  Copyright (c) Microsoft Corporation.
//  All rights reserved.
//
//  This code is licensed under the MIT License.
//
//  Permission is hereby granted, free of charge, to any person obtaining a copy
//  of this software and associated documentation files(the "Software"), to deal
//  in the Software without restriction, including without limitation the rights
//  to use, copy, modify, merge, publish, distribute, sublicense, and / or sell
//  copies of the Software, and to permit persons to whom the Software is
//  furnished to do so, subject to the following conditions :
//
//  The above copyright notice and this permission notice shall be included in
//  all copies or substantial portions of the Software.
//
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
//  THE SOFTWARE.
package com.microsoft.identity.common.java.commands;

import lombok.NonNull;

import com.microsoft.identity.common.java.WarningType;
import com.microsoft.identity.common.java.commands.parameters.CalculatorApiCommandParameters;
import com.microsoft.identity.common.java.controllers.BaseController;
import com.microsoft.identity.common.java.controllers.ExceptionAdapter;
import com.microsoft.identity.common.java.exception.BaseException;
import com.microsoft.identity.common.java.exception.ClientException;

import java.util.List;

/**
 * This command is used for the calculator ramp up Api
 * Takes in a parameters object containing the values to be computed as well as an operator,
 * returns a double result.
 */
public class CalculatorApiCommand extends BaseCommand<Double> {
    private static final String TAG = CalculatorApiCommand.class.getSimpleName();

    public CalculatorApiCommand(@NonNull CalculatorApiCommandParameters parameters,
                                 @NonNull List<BaseController> controllers,
                                 @SuppressWarnings(WarningType.rawtype_warning) @NonNull CommandCallback callback,
                                 @NonNull String publicApiId) {
        super(parameters, controllers, callback, publicApiId);
    }

    @Override
    public Double execute() throws Exception {
        for(int i = this.getControllers().size() - 1; i >= 0; i--){
            // Get the controller used to execute the command
            final BaseController controller = this.getControllers().get(i);

            // Fetch the parameters
            final CalculatorApiCommandParameters commandParameters = (CalculatorApiCommandParameters) getParameters();

            try {
                double result = controller.calculatorApiCall(commandParameters);
                return result;
            }
            catch(Exception e){
                if (e instanceof ArithmeticException | e instanceof BaseException){
                    throw ExceptionAdapter.baseExceptionFromException(e);
                }
            }
        }

        throw new ClientException(ClientException.UNKNOWN_ERROR, "No supported controllers for Calculator Api.");
    }

    @Override
    public boolean isEligibleForEstsTelemetry() {
        return true;
    }
}
