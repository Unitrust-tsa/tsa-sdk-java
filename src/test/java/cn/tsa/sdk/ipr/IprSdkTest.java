package cn.tsa.sdk.ipr;

import cn.tsa.sdk.client.OpenClient;
import cn.tsa.sdk.model.ipr.IprApplyModel;
import cn.tsa.sdk.model.ipr.IprSearchModel;
import cn.tsa.sdk.request.ipr.IprApplyRequest;
import cn.tsa.sdk.request.ipr.IprSearchRequest;
import cn.tsa.sdk.response.PageableResponse;
import cn.tsa.sdk.response.ipr.IprApplyResponse;
import cn.tsa.sdk.response.ipr.IprSearchResponse;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @author JenphyJohn
 * @date 2024/3/12 17:27
 */
public class IprSdkTest {

    String url = "https://open-test.tsa.cn";
    String appId = "ad9584deb68848ae8f3c166341fa5116";
    /** 开发者私钥 */
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
}
