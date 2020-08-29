package com.xiaofu.util.okhttp;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * @author Yang-o_o 2020-08-29 13:30
 */
@Configuration
public class OkHttpConfig {

	@Value("${ok.http.connect-timeout}")
	private Integer connectTimeout;

	@Value("${ok.http.read-timeout}")
	private Integer readTimeout;

	@Value("${ok.http.write-timeout}")
	private Integer writeTimeout;

	@Value("${ok.http.max-idle-connections}")
	private Integer maxIdleConnections;

	@Value("${ok.http.keep-alive-duration}")
	private Long keepAliveDuration;

	@Bean
	public OkHttpClient okHttpClient() {
		return new OkHttpClient.Builder()
				.sslSocketFactory(sslSocketFactory(), x509TrustManager())
				.connectionPool(pool())
				.connectTimeout(this.connectTimeout, TimeUnit.SECONDS)
				.readTimeout(this.readTimeout, TimeUnit.SECONDS)
				.writeTimeout(this.writeTimeout, TimeUnit.SECONDS)
				.hostnameVerifier((hostname, session) -> true)
				.retryOnConnectionFailure(true)
				// 拦截器
				// .addInterceptor()
				.build();
	}

	@Bean
	public X509TrustManager x509TrustManager() {
		return new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}
		};
	}

	@Bean
	public SSLSocketFactory sslSocketFactory() {
		try {
			// 信任任何链接
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, new TrustManager[] { x509TrustManager() },
					new SecureRandom());
			return sslContext.getSocketFactory();
		}
		catch (NoSuchAlgorithmException | KeyManagementException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Bean
	public ConnectionPool pool() {
		return new ConnectionPool(this.maxIdleConnections, this.keepAliveDuration,
				TimeUnit.SECONDS);
	}

}
