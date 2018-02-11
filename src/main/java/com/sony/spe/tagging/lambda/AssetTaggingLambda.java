package com.sony.spe.tagging.lambda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectTagging;
import com.amazonaws.services.s3.model.SetObjectTaggingRequest;
import com.amazonaws.services.s3.model.Tag;
import com.sony.spe.tagging.common.CommonConstants;
import com.sony.spe.tagging.proxy.request.TagAssetFileProxy;
import com.sony.spe.tagging.proxy.request.TagAssetRequestProxy;
import com.sony.spe.tagging.proxy.response.TagAssetResponse;

public class AssetTaggingLambda implements RequestHandler<TagAssetRequestProxy, TagAssetResponse> {

	private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();
	LambdaLogger Logger = null;
	
	public AssetTaggingLambda() {
	}

	// Test purpose only.
	AssetTaggingLambda(AmazonS3 s3) {
		this.s3 = s3;
	}
	
	/**
	 * Handler Method invoked by AWS Lambda.
	 */
	@Override
	public TagAssetResponse handleRequest(TagAssetRequestProxy proxy, Context context) {
		this.Logger = context.getLogger(); 
		this.Logger.log("Received event: " + proxy);
		
		String mainBucketName = System.getenv(CommonConstants.ENV_MAIN_BUCKET);
		if(null != mainBucketName && !mainBucketName.isEmpty()) {
			this.Logger.log(String.format("Identified main bucket %s from Env.", mainBucketName));
		} else {
			this.Logger.log("Couldn't identify main bucket from environment");
		}
		
		// Initiate Asset Tagging.
		this.tagAsset(proxy, mainBucketName);

		// Send Response back.
		TagAssetResponse response = new TagAssetResponse();
		response.setItemId(proxy.getItemId());
		response.setTaggedDate(new Date());

		return response;
	}

	/**
	 * Method to Initiate Asset Tagging in s3 bucket.
	 * 
	 * @param proxy
	 */
	private void tagAsset(TagAssetRequestProxy proxy, String bucketName) {
		
		List<Tag> tags = null;
		// assign tags to asset.
		for (TagAssetFileProxy file : proxy.getFiles()) {
			tags = new ArrayList<Tag>();
			tags.add(new Tag(CommonConstants.TAG_MEDIA_TYPE, proxy.getMediaType()));
			tags.add(new Tag(CommonConstants.TAG_OP_UNIT, proxy.getOpUnit()));
			tags.add(new Tag(CommonConstants.TAG_TERRITORY, proxy.getTerritory()));
			tags.add(new Tag(CommonConstants.TAG_ITEM_ID, proxy.getItemId()));
			tags.add(new Tag(CommonConstants.TAG_COMPANY_SYSTEM_NAME, proxy.getCompanySystemName()));
			tags.add(new Tag(CommonConstants.TAG_CAN_BE_ARCHIVED,
					file.getCanBeArchived() ? CommonConstants.TAG_CAN_BE_ARCHIVED_VALUE_YES
							: CommonConstants.TAG_CAN_BE_ARCHIVED_VALUE_NO));

			try {
				SetObjectTaggingRequest request = new SetObjectTaggingRequest(bucketName, file.getFileName(),
						new ObjectTagging(tags));
				// Tag object in s3.
				s3.setObjectTagging(request);
				this.Logger.log(String.format("Asset %s Tagged Successfully.", file.getFileName()));
			} catch (AmazonS3Exception ase) {
				this.Logger.log(String.format("AmazonS3Exception - Error occurred while tagging %s. Error is %s", file.getFileName(), ase));
			} catch (Exception e) {
				this.Logger.log(String.format("Exception - Error occurred while tagging %s. Error is %", file.getFileName(), e));
			}

		}

	}
}