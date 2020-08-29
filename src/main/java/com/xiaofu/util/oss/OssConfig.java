package com.xiaofu.util.oss;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Yang-o_o 2020-08-28 14:10
 */
@Component
@ConfigurationProperties(prefix = "oss")
public class OssConfig {

    private String bucketName;

    private String accessKey;

    private String accessKeySecret;

    private String endPoint;

    private String domainAddress;

    private String productClauseFilePath;

    private String tempFilePath;

    public OssConfig() {
    }

    public OssConfig(String bucketName, String accessKey, String accessKeySecret, String endPoint, String domainAddress, String productClauseFilePath, String tempFilePath) {
        this.bucketName = bucketName;
        this.accessKey = accessKey;
        this.accessKeySecret = accessKeySecret;
        this.endPoint = endPoint;
        this.domainAddress = domainAddress;
        this.productClauseFilePath = productClauseFilePath;
        this.tempFilePath = tempFilePath;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getDomainAddress() {
        return domainAddress;
    }

    public void setDomainAddress(String domainAddress) {
        this.domainAddress = domainAddress;
    }

    public String getProductClauseFilePath() {
        return productClauseFilePath;
    }

    public void setProductClauseFilePath(String productClauseFilePath) {
        this.productClauseFilePath = productClauseFilePath;
    }

    public String getTempFilePath() {
        return tempFilePath;
    }

    public void setTempFilePath(String tempFilePath) {
        this.tempFilePath = tempFilePath;
    }
}
