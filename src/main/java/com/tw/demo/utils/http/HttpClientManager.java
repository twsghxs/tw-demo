package com.tw.demo.utils.http;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpClientManager 相关执行调用方法集
 *
 * @author bing.cheng
 */
public class HttpClientManager {

	/**
	 * 初始化
	 */
	private static CloseableHttpClient httpClient;
	private static final Logger logger = LoggerFactory.getLogger(HttpClientManager.class);
	public static final String UTF_8_CHARSET = "UTF-8";
	public static RequestConfig HTTP_DEFAULT_CONFIG = RequestConfig.custom().setConnectTimeout(60000).setConnectionRequestTimeout(60000).setSocketTimeout(60000).build();

	static {
		HttpRequestRetryHandler myRetryHandler = retryHandler();
		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1", "TLSv1.1", "TLSv1.2" }, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		httpClient = HttpClientBuilder.create().setDefaultRequestConfig(HTTP_DEFAULT_CONFIG).setHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).setRetryHandler(myRetryHandler).setSSLSocketFactory(sf).build();
	}

	private static class TrustAnyTrustManager implements X509TrustManager {
		public void checkClientTrusted(X509Certificate[] chain, String authType) {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}

	}

	/**
	 * HTTP Get 获取内容
	 *
	 * @param url
	 *            请求的url地址
	 * @param requestDataMap
	 *            请求的参数
	 * @param requestHeaderMap
	 *            请求头
	 * @param config
	 *            上下文设置
	 * @param charset
	 *            编码格式
	 * @return
	 */
	public static String doGet(String url, Map<String, String> requestDataMap, Map<String, String> requestHeaderMap, RequestConfig config) {
		if (StringUtils.isBlank(url)) {
			throw new RuntimeException("http send url is null or ");
		}
		CloseableHttpResponse response = null;
		try {
			// 拼接url
			if (requestDataMap != null && !requestDataMap.isEmpty()) {
				List<NameValuePair> pairs = new ArrayList<NameValuePair>(requestDataMap.size());
				for (Map.Entry<String, String> entry : requestDataMap.entrySet()) {
					String value = entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value));
					}
				}
				if (url.indexOf('?') > 0) {
					url += "&" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, UTF_8_CHARSET));
				} else {
					url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, UTF_8_CHARSET));
				}
			}
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(config);
			// 设置请求头
			if (requestHeaderMap != null && !requestHeaderMap.isEmpty()) {
				for (Map.Entry<String, String> entry : requestHeaderMap.entrySet()) {
					String value = entry.getValue();
					if (value != null) {
						httpGet.addHeader(entry.getKey(), entry.getValue());
					}
				}
			}

			// 发送请求
			response = httpClient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if (HttpResultStatus.SUCCESS.getCode() != statusCode) {
				httpGet.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
			// 关闭相关资源
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage() + " http get error url = " + url);
			return null;
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage() + " close CloseableHttpResponse error");
			}
		}
	}

	/**
	 * 请求无入参get请求
	 *
	 * @param url
	 * @param requestHeaderMap
	 * @param config
	 * @return
	 */
	public static String doGet(String url, Map<String, String> requestHeaderMap, RequestConfig config) {
		if (StringUtils.isBlank(url)) {
			throw new RuntimeException("http send url is null or ");
		}
		CloseableHttpResponse response = null;
		try {
			// 请求参数构造
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(config);
			// 设置请求头
			if (requestHeaderMap != null && !requestHeaderMap.isEmpty()) {
				for (Map.Entry<String, String> entry : requestHeaderMap.entrySet()) {
					String value = entry.getValue();
					if (value != null) {
						httpGet.addHeader(entry.getKey(), entry.getValue());
					}
				}
			}

			// 发送请求
			response = httpClient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if (HttpResultStatus.SUCCESS.getCode() != statusCode) {
				httpGet.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}

			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, UTF_8_CHARSET);
			}

			// 关闭相关资源
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage() + " http get error url = " + url);
			return null;
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage() + " close CloseableHttpResponse error");
			}
		}
	}

	/**
	 * HTTP Post 获取内容
	 *
	 * @param url
	 *            请求的url地址
	 * @param requestParams
	 *            请求的参数
	 * @param charset
	 *            编码格式
	 * @param requestHeaderMap
	 *            请求头
	 * @param config
	 *            上下文
	 * @return 页面内容
	 */
	public static String doPost(String url, Map<String, String> requestParams, String charset, Map<String, String> requestHeaderMap, RequestConfig config) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		CloseableHttpResponse response = null;
		try {
			List<NameValuePair> pairs = null;
			if (null != requestParams) {
				pairs = new ArrayList<>();
				for (String key : requestParams.keySet()) {
					pairs.add(new BasicNameValuePair(key, requestParams.get(key)));
				}
			}
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(config);
			// 设置请求头`
			if (requestHeaderMap != null && !requestHeaderMap.isEmpty()) {
				for (Map.Entry<String, String> entry : requestHeaderMap.entrySet()) {
					String value = entry.getValue();
					if (value != null) {
						httpPost.addHeader(entry.getKey(), entry.getValue());
					}
				}
			}
			if (pairs != null && pairs.size() > 0) {
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8_CHARSET));
			}
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage() + " http post error url = " + url);
			return null;
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage() + " close CloseableHttpResponse error");
			}
		}
	}

	/**
	 * 请求post请求
	 *
	 * @param url
	 * @param requestBody
	 * @param requestHeaderMap
	 * @param config
	 * @return
	 */
	public static String doPost(String url, String requestBody, Map<String, String> requestHeaderMap, RequestConfig config) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		CloseableHttpResponse response = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(config);
			// 设置请求头
			if (requestHeaderMap != null && !requestHeaderMap.isEmpty()) {
				for (Map.Entry<String, String> entry : requestHeaderMap.entrySet()) {
					String value = entry.getValue();
					if (value != null) {
						httpPost.addHeader(entry.getKey(), entry.getValue());
					}
				}
			}
			if (requestBody != null) {
				httpPost.setEntity(new StringEntity(requestBody, UTF_8_CHARSET));
			}
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage() + " http post error url = " + url);
			return null;
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage() + " close CloseableHttpResponse error");
			}
		}
	}

	/**
	 * 自定义handler重试
	 */
	private static HttpRequestRetryHandler retryHandler() {
		HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {
			@Override
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				// Do not retry if over max retry count
				if (executionCount >= 3) {
					return false;
				}
				// Timeout
				if (exception instanceof InterruptedIOException) {
					logger.error("InterruptedIOException has retry request:" + context.toString() + "][executionCount:" + executionCount + "]");
					return true;
				}
				// Unknown host
				if (exception instanceof UnknownHostException) {
					return true;
				}
				// Connection refused
				if (exception instanceof ConnectTimeoutException) {
					logger.error("ConnectTimeoutException has retry request:" + context.toString() + "][executionCount:" + executionCount + "]");
					return true;
				}
				// SSL handshake exception
				if (exception instanceof SSLException) {
					return true;
				}
				// exception retry
				if (exception instanceof Exception) {
					return true;
				}
				HttpClientContext clientContext = HttpClientContext.adapt(context);
				HttpRequest request = clientContext.getRequest();
				boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
				// Retry if the request is considered idempotent
				if (idempotent) {
					return true;
				}
				return false;
			}
		};
		return myRetryHandler;
	}
}
