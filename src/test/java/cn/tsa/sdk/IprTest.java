package cn.tsa.sdk;


import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.tsa.sdk.client.OpenClient;
import cn.tsa.sdk.model.ipr.IprApplyModel;
import cn.tsa.sdk.model.ipr.IprSearchModel;
import cn.tsa.sdk.request.ipr.IprApplyRequest;
import cn.tsa.sdk.request.ipr.IprSearchRequest;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.File;

/**
 * 版权接口测试
 */
public class IprTest {

    private static final String url = "";
    private static final String appId = "";
    private static final String privateKey = "";


    @Test
    public void testApply() {
        // 方法名
        String methodName = "ipr.opus.apply";
        OpenClient client = new OpenClient(url, appId, privateKey);
        IprApplyRequest request = new IprApplyRequest(methodName);
        String filePath = "/Users/hongwei/Downloads/debug-demo.zip";
        File file = new File(filePath);
        Digester sha256 = new Digester(DigestAlgorithm.SHA256);
        String fileHash = sha256.digestHex(file);
        String remark = "The Spring Framework provides a comprehensive programming and configuration model for modern Java-based enterprise applications - on any kind of deployment platform.";

        // 业务参数
        IprApplyParam iprApplyParam = new IprApplyParam();
        iprApplyParam.setFileHash(fileHash);
        iprApplyParam.setOpusDescribe(remark);
        iprApplyParam.setOpusName(file.getName());
        iprApplyParam.setFileType(102);
        iprApplyParam.setApplyName("SuperMan");
        iprApplyParam.setApplyIdType(101);
        iprApplyParam.setApplyIdNumber("123456");
        iprApplyParam.setOpusState(1);
        iprApplyParam.setOpusPartnerId("1234567890");
        iprApplyParam.setOpusLabel("风景,山水");
        iprApplyParam.setOpusStore(106);
        iprApplyParam.setOpusType(111);
        iprApplyParam.setOpusCreativeType(103);
        iprApplyParam.setOpusCreativeNature(102);
        iprApplyParam.setApplyType(102);
        iprApplyParam.setApplyNationality("CN");
        iprApplyParam.setApplyPhone("13812312312");
        iprApplyParam.setApplyMail("zhanghw0212@163.com");
        iprApplyParam.setApplyAddress("辽宁省沈阳市沈河区新地中心2号楼");
        iprApplyParam.setApplyEmergencyName("张宏伟");
        iprApplyParam.setApplyEmergencyPhone("13811111111");
        iprApplyParam.setRemark(remark);
        iprApplyParam.setApplyUserType(101);
        iprApplyParam.setTemplateType(2);

        iprApplyParam.setSourceFileUploadType(0);
        request.setBizModel(iprApplyParam);

        IprApplyModel iprApplyModel = client.execute(request);
        System.out.println("iprApplyModel=" + JSON.toJSON(iprApplyModel));
    }

    @Test
    public void testSearch() {

        // 方法名
        OpenClient client = new OpenClient(url, appId, privateKey);
        String methodName = "ipr.opus.search";
        IprSearchRequest request = new IprSearchRequest(methodName);
        // 业务参数
        IprSearchParam iprSearchParam = new IprSearchParam();
        iprSearchParam.setPage(1);
        iprSearchParam.setSize(20);
//        iprSearchParam.setFileHash("9A7585F1F582C28D421FA914A1D80A8B07232CBFF76C30BA59D5E8B8859A2F3F");
//        iprSearchParam.setExternalUserInfo("15140110064");

        // 发送请求
        request.setBizModel(iprSearchParam);
        IprSearchModel iprSearchModel = client.execute(request);
        System.out.println("iprSearchModel=" + JSON.toJSON(iprSearchModel));
    }

}
