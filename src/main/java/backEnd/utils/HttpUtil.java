package backEnd.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class HttpUtil {

    private HttpClient client;

    public HttpUtil() {
        client = HttpClients.createDefault();
    }

    /**
     * 向 url 发送post请求
     *
     * @param url    目标链接
     * @param params 请求所带的参数表
     * @return  本次请求的返回值
     */
    public String postURL(String url, HashMap<String, String> params) {
        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
        if (params != null)
            for (String key : params.keySet()) {
                pairList.add(new BasicNameValuePair(key, params.get(key)));
            }

        try {
            HttpEntity reqEntity = new UrlEncodedFormEntity(pairList, "utf-8");
            RequestConfig requestConfig = getRequestConfig();

            HttpPost post = new HttpPost(url);
            post.setEntity(reqEntity);
            post.setConfig(requestConfig);
            HttpResponse response = client.execute(post);
            return handlerResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 向 url 发送post请求
     *
     * @param url    目标链接
     * @param requestStr 请求的内容
     * @return  本次请求的返回值
     */
    public String postURL(String url, String requestStr) {
        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
        if (requestStr == null)
            requestStr = "";

        try {
            HttpEntity reqEntity = new StringEntity(requestStr, "utf-8");
            RequestConfig requestConfig = getRequestConfig();

            HttpPost post = new HttpPost(url);
            post.setEntity(reqEntity);
            post.setConfig(requestConfig);
            HttpResponse response = client.execute(post);
            return handlerResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 向 url 发送get请求
     * @param url   请求链接
     * @param params    请求所带参数
     * @return  本次请求的返回值
     */
    public String getURL(String url, HashMap<String, String> params) {
        StringBuilder string = new StringBuilder();
        string.append(url);
        if (!url.endsWith("/") || !url.endsWith("\\")) {
            string.append("/");
        }

        int index = 0;
        for (String key : params.keySet()) {
            if (index++ == 0)
                string.append("?");
            else
                string.append("&");
            string.append(key).append("=").append(params.get(key));
        }

        try {

            RequestConfig requestConfig = getRequestConfig();
            HttpGet get = new HttpGet(url);
            get.setConfig(requestConfig);
            HttpResponse response = client.execute(get);
            return handlerResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成请求配置参数
     * @return  请求配置实体
     */
    private RequestConfig getRequestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(5000)        // 一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间
                .setSocketTimeout(5000)         // 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间
                .setConnectionRequestTimeout(5000)
                .build();
    }
    /**
     * 处理请求返回值
     * @param response  请求响应
     * @return  响应的内容,为null表示请求失败
     * @throws IOException
     */
    private String handlerResponse(HttpResponse response) throws IOException {
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity resEntity = response.getEntity();
            return EntityUtils.toString(resEntity, "utf-8");
        } else return null;
    }
}
