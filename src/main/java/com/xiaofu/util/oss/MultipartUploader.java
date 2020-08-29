package com.xiaofu.util.oss;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.internal.OSSHeaders;
import com.aliyun.oss.model.CompleteMultipartUploadRequest;
import com.aliyun.oss.model.CompleteMultipartUploadResult;
import com.aliyun.oss.model.InitiateMultipartUploadRequest;
import com.aliyun.oss.model.InitiateMultipartUploadResult;
import com.aliyun.oss.model.ListPartsRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PartETag;
import com.aliyun.oss.model.PartListing;
import com.aliyun.oss.model.PartSummary;
import com.aliyun.oss.model.StorageClass;
import com.aliyun.oss.model.UploadPartRequest;
import com.aliyun.oss.model.UploadPartResult;

/**
 * @author Yang-o_o 2020-08-28 14:10
 */
public class MultipartUploader implements Runnable {

	private static String bucketName;

	private static String key;

	private MultipartFile file;

	private long startPos;

	private long partSize;

	private int partNumber;

	private String uploadId;

	static List<PartETag> partETags = Collections
			.synchronizedList(new ArrayList<PartETag>());

	MultipartUploader(String bucketName, String key, MultipartFile file, long startPos,
                      long partSize, int partNumber, String uploadId) {
		MultipartUploader.bucketName = bucketName;
		MultipartUploader.key = key;
		this.file = file;
		this.startPos = startPos;
		this.partSize = partSize;
		this.partNumber = partNumber;
		this.uploadId = uploadId;

	}

	@Override
	public void run() {
		InputStream in = null;
		try {
			// 获取文件流
			in = this.file.getInputStream();
			// 跳到每个分块的开头
			long skip = in.skip(this.startPos);

			// 创建UploadPartRequest，上传分块
			UploadPartRequest uploadPartRequest = new UploadPartRequest();
			uploadPartRequest.setBucketName(bucketName);
			uploadPartRequest.setKey(key);
			uploadPartRequest.setUploadId(this.uploadId);
			uploadPartRequest.setInputStream(in);
			uploadPartRequest.setPartSize(this.partSize);
			uploadPartRequest.setPartNumber(this.partNumber);

			UploadPartResult uploadPartResult = OssUtil.client
					.uploadPart(uploadPartRequest);
			synchronized (this) {
				// 将返回的PartETag保存到List中。
				partETags.add(uploadPartResult.getPartETag());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (in != null) {
				try {
					// 关闭文件流
					in.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 初始化分块上传事件并生成uploadId，用来作为区分分块上传事件的唯一标识.
	 * @param contentType contentType
	 * @param bucketName bucketName
	 * @param key key
	 * @return uploadId
	 */
	static String claimUploadId(String contentType, String bucketName, String key) {
		InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(
				bucketName, key);
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS,
				StorageClass.Standard.toString());
		metadata.setContentType(contentType);
		request.setObjectMetadata(metadata);

		InitiateMultipartUploadResult result = OssUtil.client
				.initiateMultipartUpload(request);

		return result.getUploadId();
	}

	/**
	 * 将文件分块进行升序排序并执行文件上传.
	 * @param uploadId uploadId
	 */
	static void completeMultipartUpload(String uploadId) {
		// 将文件分块按照升序排序
		partETags.sort(Comparator.comparingInt(PartETag::getPartNumber));

		CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(
				bucketName, key, uploadId, partETags);
		// 完成分块上传
		OssUtil.client.completeMultipartUpload(completeMultipartUploadRequest);
	}

}
