package com.xiaofu.util.oss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyun.oss.OSSClient;

/**
 * @author Yang-o_o 2020-08-28 14:10
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
