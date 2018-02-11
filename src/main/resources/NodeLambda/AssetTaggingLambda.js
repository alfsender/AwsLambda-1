/**
 * This function is invoked through API Gateway to tag assets in s3.
 * 
 * 1: All tags that needs to be applieda are mentioned in request param part of body.
 * 2: Function will iterate through all objects mentioned in request and assign tags on each file.
 * 3: If object is not present in s3/any error occurr while tagging object function will log that error and continue to next
 * object for tagging.
 * 
 */

'use strict';
const AWS = require('aws-sdk');
const s3 = new AWS.S3();

var mainBucketName = process.env.ENV_MAIN_BUCKET;
exports.handler = (event, context, callback) => {
    console.log("Received event to tag assets." + event.body);
    if (!mainBucketName) {
        console.log("Could not identify main bucket name.");
    }
    else {
        console.log('Identified main bucket %s from Env.', mainBucketName);
        initiateTagging(JSON.parse(event.body));
    }
    callback(null, { "statusCode": 200, "body": "" });
};

/**
 * Function to initiate tagging of assets received in req body. 
 *
 */
var initiateTagging = (body) => {
    console.log("Received event body %s.", body);
    console.log("Tags [itemId: %s, mediaType: %s, opUnit: %s, territory: %s, companySystemName: %s]",
        body.itemId, body.mediaType, body.opUnit, body.territory, body.companySystemName);

    body.files.forEach((file) => {
        var params = getCommonTaggingObject(body);
        // set fileName/objectName in request params.
        params.Key = file.fileName;

        // set CanBeArchived tag for each file.
        var canBeArchivedTag = {
            Key: "CanBeArchived",
            Value: file.canBeArchived
        }
        params.Tagging.TagSet.push(canBeArchivedTag);

        s3.putObjectTagging(params, function(err, data) {
            if (err) {
                console.log("Error occurred while tagging file :" + file.fileName + " - " + err, err.stack);
            }
            else {
                console.log("Object %s/%s tagged successfully.", mainBucketName, file.fileName);
            }
        });
    });
}

/**
 * Common method to get request payload to tag assets in s3.
 */
var getCommonTaggingObject = (body) => {
    var params = {
        Bucket: mainBucketName,
        Tagging: {
            TagSet: [{
                    Key: "ItemId",
                    Value: body.itemId
                },
                {
                    Key: "MediaType",
                    Value: body.mediaType
                },
                {
                    Key: "OpUnit",
                    Value: body.opUnit
                },
                {
                    Key: "Territory",
                    Value: body.territory
                },
                {
                    Key: "CompanySystemName",
                    Value: body.companySystemName
                }
            ]
        }
    }
    return params;
}
