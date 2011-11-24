package com.lazerycode.ebselen.customhandlers;

import com.lazerycode.ebselen.EbselenCore;
import org.jets3t.service.*;
import org.jets3t.service.impl.rest.httpclient.*;
import org.jets3t.service.model.*;
import org.jets3t.service.security.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to create a compressed zip file for use by dropbox
 *
 * @author MPLC
 */
public class sThree {

    private String awsAccessKey;// = get("s3keyid");
    private String awsSecretKey;// = get("s3secretkey");
    private String s3BucketName;// = get("s3bucket");
    private String currentS3Bucket = null;
    private AWSCredentials awsCredentials;
    private S3Service s3Service;
    private S3Bucket[] myBuckets;
    private static final Logger logger = LoggerFactory.getLogger(EbselenCore.class);

    /**
     * S3 Constructor - sets up a basic S3 so we can check the status of S3 uploads
     */
    public sThree() {
        awsCredentials = new AWSCredentials(awsAccessKey, awsSecretKey);
        try {
            s3Service = new RestS3Service(awsCredentials);
        } catch (S3ServiceException Ex) {
            logger.error("Unable to initialise S3 Service: " + Ex.getS3ErrorMessage());
        }
        try {
            myBuckets = s3Service.listAllBuckets();
        } catch (S3ServiceException Ex) {
            logger.error("Unable to list S3 Buckets: " + Ex.getS3ErrorMessage());
        }
    }

    public String getCurrentS3Bucket() throws S3ServiceException {
        return currentS3Bucket;
    }

    public void setCurrentS3Bucket(String bucketName) throws S3ServiceException {
        currentS3Bucket = bucketName;
    }

    /**
     * Checks to see if an object exists in the S3 bucket for the current environment
     *
     * @param item the name of the item we are looking for.
     * @return true if exists otherwise false.
     * @throws S3ServiceException
     */
    public boolean objectExists(String item) throws ServiceException {
        S3Object[] availableItems;
        try {
            availableItems = s3Service.listObjects(s3BucketName);
        } catch (S3ServiceException Ex) {
            logger.error("Unable to find " + item);
            return false;
        }
        // Workaround because the API cannot seem to access a bucket with a . in its name
        for (int i = 0; i < availableItems.length; i++) {
            if (availableItems[i].getKey().toString().equals(item)) {
                logger.debug("Found '" + item + "' on S3!");
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes an object from S3
     *
     * @param item the name of the item we are trying to delete.
     * @throws S3ServiceException
     */
    public void deleteObject(String item) throws ServiceException {
        try {
            s3Service.deleteObject(s3BucketName, item);
        } catch (ServiceException Ex) {
            logger.error("Unable to delete object : " + Ex.getErrorMessage());
        }
    }

    /**
     * Close S3 Connection and clean up
     *
     * @throws S3ServiceException
     */
    public void end() throws ServiceException {
        s3Service.shutdown();
    }
}
