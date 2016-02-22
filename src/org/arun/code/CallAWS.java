/**
 *
 */
package org.arun.code;

import java.nio.ByteBuffer;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambdaClient;
import com.amazonaws.services.lambda.model.InvocationType;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;

/**
 * @author arunv
 *
 */
public class CallAWS {

	private void init() throws Exception {

		AWSLambdaClient lambdaClient;
		AWSCredentials credentials = null;
		try {
			// credentials = new ProfileCredentialsProvider().getCredentials();
			credentials = new BasicAWSCredentials("<AccessID>", "<AccessKey>");
		} catch (Exception e) {

		}

		lambdaClient = new com.amazonaws.services.lambda.AWSLambdaAsyncClient(credentials);

		lambdaClient.setRegion(Region.getRegion(Regions.US_WEST_2));

		InvokeRequest request = new InvokeRequest();
		request.setFunctionName("<Lambda Function Call>");
		request.setInvocationType(InvocationType.RequestResponse);
		request.setPayload("{\"obj1\" : 12, \"obj2\" : 35}");

		InvokeResult result = lambdaClient.invoke(request);
		ByteBuffer buffer = result.getPayload();

		String resultstr = new String(buffer.array(), "UTF-8");

		System.out.println(resultstr);

	}

	public static void main(String[] args) {
		try {
			CallAWS obj = new CallAWS();
			obj.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
