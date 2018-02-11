package com.sony.spe.tagging.lambda;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.sony.spe.tagging.proxy.request.AssetRestoreRequest;
import com.sony.spe.tagging.proxy.response.AssetRestoreIntermediateResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class InitiateRestoreLambdaTest {

    private static AssetRestoreRequest input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = null;
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testInitiateRestoreLambda() {
        InitiateRestoreLambda handler = new InitiateRestoreLambda();
        Context ctx = createContext();

        AssetRestoreIntermediateResponse output = handler.handleRequest(input, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals("Hello from Lambda!", output);
    }
}
