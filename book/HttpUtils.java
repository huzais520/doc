package com.core.bo;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;

/**
 * Created by scn7th on 2023/7/21.
 */
public class HttpUtils {

    private static CloseableHttpClient httpClient = HttpClients.custom().build();

    /**
     * 与Spring集成初始化HttpClient
     * 在Spring启动后会调用init方法
     */
    /**
     * application-json方式提交
     * @param url http路径
     * @param json json字符串 {"key":"value"}
     * @return 响应内容字符串
     */
    public static String postJson(String url, String json) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        try(CloseableHttpResponse response = httpClient.execute(httpPost)) {
            return EntityUtils.toString( response.getEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * application/x-www-form-urlencoded 方式提交
     * @param url http路径
     * @param kvMap key与value组成的map
     * @return 响应内容字符串
     */
    public static String postForm(String url, Map<String, String> kvMap) {
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<>();
        kvMap.forEach((key, value) -> nvps.add(new BasicNameValuePair(key, value)));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        try(CloseableHttpResponse response = httpClient.execute(httpPost)) {
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * get请求
     * @param url http路径 (后可跟参数?p1=aaa&p2=bbb&p3=ccc)
     * @return 响应内容字符串
     */
    public static String getString(String url) {
        HttpGet httpget = new HttpGet(url);
        try(CloseableHttpResponse response = httpClient.execute(httpget)) {
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * get请求
     * @param url http路径 (后可跟参数?p1=aaa&p2=bbb&p3=ccc)
     * @return 响应内容
     */
    public static byte[] getBytes(String url) {
        HttpGet httpget = new HttpGet(url);
        try(CloseableHttpResponse response = httpClient.execute(httpget)) {
            return EntityUtils.toByteArray(response.getEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * application-json方式提交
     * @param url http路径
     * @param json json字符串 {"key":"value"}
     * @return 响应内容字符串
     */
    public static String putJson(String url, String json,Map<String, String> headerMap) {
        HttpPut httpPut = new HttpPut(url);
        headerMap.entrySet().forEach(entry->httpPut.addHeader(entry.getKey(), entry.getValue()));
        httpPut.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        try(CloseableHttpResponse response = httpClient.execute(httpPut)) {
            return EntityUtils.toString( response.getEntity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

