package edu.swpu.modules.portrait.aliyunNlp;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;

import com.aliyuncs.alinlp.model.v20200629.GetWsChGeneralRequest;
import com.aliyuncs.alinlp.model.v20200629.GetWsChGeneralResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

//中文分词服务
@Component
public class ChineseWordSegmentation {
    public String getWords(String comment) throws ClientException {
        String accessKeyId = System.getenv("NLP_AK_ENV");
        String accessKeySecret = System.getenv("NLP_SK_ENV");
        DefaultProfile defaultProfile = DefaultProfile.getProfile(
                "cn-hangzhou",
                accessKeyId,
                accessKeySecret);
        IAcsClient client = new DefaultAcsClient(defaultProfile);
        //构造请求参数，其中GetPosChEcom是算法的actionName, 请查找对应的《API基础信息参考》文档并替换为您需要的算法的ActionName，示例详见下方文档中的：更换API请求

        GetWsChGeneralRequest request = new GetWsChGeneralRequest();
        //固定值，无需更改
        request.setSysEndpoint("alinlp.cn-hangzhou.aliyuncs.com");
        //固定值，无需更改
        request.setServiceCode("alinlp");
        //请求参数, 具体请参考《API基础信息文档》进行替换与填写
        request.setText(comment);
        request.setTokenizerId("GENERAL_CHN");
        long start = System.currentTimeMillis();
        //获取请求结果，注意这里的GetPosChEcom也需要替换
        GetWsChGeneralResponse response = client.getAcsResponse(request);
        System.out.println(response.hashCode());
        System.out.println(response.getRequestId() + "\n" + response.getData() + "\n"
                + "cost:" + (System.currentTimeMillis() - start));
        String responseData = response.getData();
        return responseData;
    }
}
