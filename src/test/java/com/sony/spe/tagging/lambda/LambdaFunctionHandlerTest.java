package com.sony.spe.tagging.lambda;

import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.sony.spe.tagging.proxy.request.TagAssetRequestProxy;
import com.sony.spe.tagging.proxy.response.TagAssetResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
@RunWith(MockitoJUnitRunner.class)
public class LambdaFunctionHandlerTest {

    private final String OUTPUT_TEXT = "Process Completed";
    private S3Event event;
    private TagAssetRequestProxy proxy;

    @Mock
    private AmazonS3 s3Client;

    @Captor
    private ArgumentCaptor<GetObjectRequest> getObjectRequest;

    @Before
    public void setUp() throws IOException {
    		proxy = TestUtils.parse("/tag_request.json", TagAssetRequestProxy.class); 	
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testLambdaFunctionHandler() {
        // LambdaFunctionHandler handler = new LambdaFunctionHandler(s3Client);
    		AssetTaggingLambda handler = new AssetTaggingLambda(s3Client);
        Context ctx = createContext();

        TagAssetResponse output = handler.handleRequest(proxy, ctx);

        // TODO: validate output here if needed.
        Assert.assertNotNull(output);
        Assert.assertEquals(proxy.getItemId(), output.getItemId());
    }
}
