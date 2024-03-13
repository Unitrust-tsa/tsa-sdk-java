package cn.tsa.sdk.ipr;

import cn.tsa.sdk.client.OpenClient;
import cn.tsa.sdk.model.ipr.IprApplyModel;
import cn.tsa.sdk.model.ipr.IprApplySourceModel;
import cn.tsa.sdk.model.ipr.IprDownloadModel;
import cn.tsa.sdk.model.ipr.IprSearchModel;
import cn.tsa.sdk.request.ipr.IprApplyRequest;
import cn.tsa.sdk.request.ipr.IprApplySourceRequest;
import cn.tsa.sdk.request.ipr.IprDownloadRequest;
import cn.tsa.sdk.request.ipr.IprSearchRequest;
import cn.tsa.sdk.response.PageableResponse;
import cn.tsa.sdk.response.ipr.IprApplyResponse;
import cn.tsa.sdk.response.ipr.IprApplySourceResponse;
import cn.tsa.sdk.response.ipr.IprDownloadResponse;
import cn.tsa.sdk.response.ipr.IprSearchResponse;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @author JenphyJohn
 * @date 2024/3/12 17:27
 */
public class IprSdkTest {

    String url = "";

    String appId = "";
    /**
     * 开发者私钥
     */
    String privateKeyIsv = "";

    // 声明一个就行
    OpenClient client = new OpenClient(url, appId, privateKeyIsv);

    @Test
    public void testApply() {
        // 创建请求对象
        IprApplyRequest request = new IprApplyRequest();
        // 请求参数
        IprApplyModel model = new IprApplyModel();
        model.setFileType(102);
        request.setBizModel(model);

        // 发送请求
        IprApplyResponse response = client.execute(request);

        if (response.isSuccess()) {
            // 返回结果
            System.out.printf("response:%s%n",
                    JSON.toJSONString(response));
        } else {
            System.out.println("错误，subCode:" + response.getSubCode() + ", subMsg:" + response.getSubMsg());
        }
    }

    @Test
    public void testApplySource() {
        // 创建请求对象
        IprApplySourceRequest request = new IprApplySourceRequest();
        // 请求参数
        IprApplySourceModel model = new IprApplySourceModel();
        model.setFileType(102);
        request.setBizModel(model);

        // 发送请求
        IprApplySourceResponse response = client.execute(request);

        if (response.isSuccess()) {
            // 返回结果
            System.out.printf("response:%s%n",
                    JSON.toJSONString(response));
        } else {
            System.out.println("错误，subCode:" + response.getSubCode() + ", subMsg:" + response.getSubMsg());
        }
    }



    @Test
    public void testSearch() {
        // 创建请求对象
        IprSearchRequest request = new IprSearchRequest();
        // 请求参数
        IprSearchModel model = new IprSearchModel();
        model.setPage(1);
        model.setSize(5);
        request.setBizModel(model);

        // 发送请求
        PageableResponse<IprSearchResponse> response = client.execute(request);

        if (response.isSuccess()) {
            // 返回结果
            System.out.printf("response:%s%n",
                    JSON.toJSONString(response));
        } else {
            System.out.println("错误，subCode:" + response.getSubCode() + ", subMsg:" + response.getSubMsg());
        }
    }

    @Test
    public void testDownload() {
        IprDownloadRequest request = new IprDownloadRequest();
        IprDownloadModel iprDownloadModel = new IprDownloadModel();
        iprDownloadModel.setOid("56f0faaeb1a64aeea8185f86d25f2323");
        // 发送请求
        request.setBizModel(iprDownloadModel);
        IprDownloadResponse iprDownloadResponse = client.execute(request);
        System.out.println("iprDownloadResponse=" + JSON.toJSON(iprDownloadResponse));
    }
}
