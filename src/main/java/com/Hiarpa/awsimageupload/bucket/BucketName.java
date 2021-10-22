package com.Hiarpa.awsimageupload.bucket;

public enum BucketName {

    PROFILE_IMAGE("java-image-upload");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
