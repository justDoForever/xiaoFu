package com.xiaofu.util.oss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyun.oss.OSSClient;

/**
 * OssClientFactory.
 *
 * @author Yang 2019-12-16 10:41
 */
@Component
public class OssClientFactory {

	private static OSSClient ossClient = null;

	@Autowired
	private OssConfig ossConfig;

	public OSSClient getInstance() {
		if (ossClient == null) {
			synchronized (this) {
				if (ossClient == null) {
					ossClient = new OSSClient(this.ossConfig.getEndPoint(),
							this.ossConfig.getAccessKey(),
							this.ossConfig.getAccessKeySecret());
				}
			}
		}
		return ossClient;
	}

}
