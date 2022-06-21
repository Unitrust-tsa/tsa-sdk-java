package cn.tsa.sdk.client;

import cn.tsa.sdk.common.OpenConfig;
import cn.tsa.sdk.common.UploadFile;
import cn.tsa.sdk.constant.RequestMethod;
import okhttp3.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * HTTP请求工具
 *
 * @author JenphyJohn
 */
public class OpenHttp {

    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    private Map<String, List<Cookie>> cookieStore = new HashMap<>();

    private OkHttpClient httpClient;

    public OpenHttp() {
        this(new OpenConfig());
    }

    public OpenHttp(OpenConfig openConfig) {
        this.initHttpClient(openConfig);
    }

    protected void initHttpClient(OpenConfig openConfig) {
        httpClient = new OkHttpClient.Builder()
                // 设置链接超时时间
                .connectTimeout(openConfig.getConnectTimeoutSeconds(), TimeUnit.SECONDS)
                .readTimeout(openConfig.getReadTimeoutSeconds(), TimeUnit.SECONDS)
                .writeTimeout(openConfig.getWriteTimeoutSeconds(), TimeUnit.SECONDS)
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                        cookieStore.put(httpUrl.host(), list);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                        List<Cookie> cookies = cookieStore.get(httpUrl.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                }).build();
    }

    /**
     * get请求
     *
     * @param url
     * @param header
     * @return
     * @throws IOException
     */
    public String get(String url, Map<String, String> header) throws IOException {
        Request.Builder builder = new Request.Builder().url(url).get();
        // 添加header
        addHeader(builder, header);
        Request request = builder.build();
        Response response = httpClient.newCall(request).execute();
        return response.body() != null ? response.body().string() : null;
    }

    /**
     * 请求json数据，contentType=application/json
     *
     * @param url    请求路径
     * @param json   json数据
     * @param header header
     * @return 返回响应结果
     * @throws IOException
     */
    public String requestJson(String url, String json, Map<String, String> header) throws IOException {
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, json);
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(body);
        // 添加header
        addHeader(requestBuilder, header);
        Request request = requestBuilder.build();
        try (Response response = httpClient
                .newCall(request)
                .execute()) {
            return response.body() != null ? response.body().string() : null;
        }
    }

    /**
     * 提交表单
     *
     * @param url    url
     * @param form   参数
     * @param header header
     * @param method 请求方式，post，get等
     * @return
     * @throws IOException
     */
    public String request(String url, Map<String, String> form, Map<String, String> header, RequestMethod method) throws IOException {
        Request.Builder requestBuilder = buildRequestBuilder(url, form, method);
        // 添加header
        addHeader(requestBuilder, header);
        Request request = requestBuilder.build();
        try (Response response = httpClient
                .newCall(request)
                .execute()) {
            return response.body() != null ? response.body().string() : null;
        }
    }

    public static Request.Builder buildRequestBuilder(String url, Map<String, String> form, RequestMethod method) {
        switch (method) {
            case GET:
                return new Request.Builder()
                        .url(buildHttpUrl(url, form))
                        .get();
            case HEAD:
                return new Request.Builder()
                        .url(buildHttpUrl(url, form))
                        .head();
            case PUT:
                return new Request.Builder()
                        .url(url)
                        .put(buildFormBody(form));
            case DELETE:
                return new Request.Builder()
                        .url(url)
                        .delete(buildFormBody(form));
            default:
                return new Request.Builder()
                        .url(url)
                        .post(buildFormBody(form));
        }
    }

    public static HttpUrl buildHttpUrl(String url, Map<String, String> form) {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        for (Map.Entry<String, String> entry : form.entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        return urlBuilder.build();
    }

    public static FormBody buildFormBody(Map<String, String> form) {
        FormBody.Builder paramBuilder = new FormBody.Builder(StandardCharsets.UTF_8);
        for (Map.Entry<String, String> entry : form.entrySet()) {
            paramBuilder.add(entry.getKey(), entry.getValue());
        }
        return paramBuilder.build();
    }

    /**
     * 提交表单，并且上传文件
     *
     * @param url
     * @param form
     * @param header
     * @param files
     * @return
     * @throws IOException
     */
    public String requestFile(String url, Map<String, String> form, Map<String, String> header, List<UploadFile> files)
            throws IOException {
        // 创建MultipartBody.Builder，用于添加请求的数据
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
        bodyBuilder.setType(MultipartBody.FORM);

        for (UploadFile uploadFile : files) {

            bodyBuilder.addFormDataPart(
                    // 请求的名字
                    uploadFile.getName(),
                    // 文件的文字，服务器端用来解析的
                    uploadFile.getFileName(),
                    // 创建RequestBody，把上传的文件放入
                    RequestBody.create(null, uploadFile.getFileData())
            );
        }

        Set<Map.Entry<String, String>> entrySet = form.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            bodyBuilder.addFormDataPart(entry.getKey(), entry.getValue());
        }

        RequestBody requestBody = bodyBuilder.build();
        Request.Builder builder = new Request.Builder().url(url).post(requestBody);
        // 添加header
        addHeader(builder, header);
        Request request = builder.build();
        try (Response response = httpClient.newCall(request).execute()) {
            return response.body() != null ? response.body().string() : null;
        }
    }

    private void addHeader(Request.Builder builder, Map<String, String> header) {
        if (header != null) {
            Set<Map.Entry<String, String>> entrySet = header.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                builder.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
    }

    public void setCookieStore(Map<String, List<Cookie>> cookieStore) {
        this.cookieStore = cookieStore;
    }

    public void setHttpClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

}
