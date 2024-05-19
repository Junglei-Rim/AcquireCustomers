package edu.swpu.modules.portrait.aliyunNlp;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alinlp.model.v20200629.GetPosChEcomRequest;
import com.aliyuncs.alinlp.model.v20200629.GetPosChEcomResponse;
import com.aliyuncs.alinlp.model.v20200629.GetSaChGeneralRequest;
import com.aliyuncs.alinlp.model.v20200629.GetSaChGeneralResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import edu.swpu.common.utils.R;
import org.springframework.stereotype.Component;

@Component
public class EmotionAnalysis {


    /**
     * 代码示例：请求词性标注算法
     */

    /**
     * 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
     * 此处以把AccessKey和AccessKeySecret保存在环境变量为例说明。您也可以根据业务需要，保存到配置文件里。
     * 强烈建议不要把AccessKey和AccessKeySecret保存到代码里，会存在密钥泄漏风险
     */
    public R getEmotional(String comment) throws ClientException {
        String accessKeyId = System.getenv("NLP_AK_ENV");
        String accessKeySecret = System.getenv("NLP_SK_ENV");
        DefaultProfile defaultProfile = DefaultProfile.getProfile(
                "cn-hangzhou",
                accessKeyId,
                accessKeySecret);
        IAcsClient client = new DefaultAcsClient(defaultProfile);
        //构造请求参数，其中GetPosChEcom是算法的actionName, 请查找对应的《API基础信息参考》文档并替换为您需要的算法的ActionName，示例详见下方文档中的：更换API请求

        GetSaChGeneralRequest request = new GetSaChGeneralRequest();
        //固定值，无需更改
        request.setSysEndpoint("alinlp.cn-hangzhou.aliyuncs.com");
        //固定值，无需更改
        request.setServiceCode("alinlp");
        //请求参数, 具体请参考《API基础信息文档》进行替换与填写
        request.setText(comment);
        long start = System.currentTimeMillis();
        //获取请求结果，注意这里的GetPosChEcom也需要替换
        GetSaChGeneralResponse response = client.getAcsResponse(request);
        System.out.println(response.hashCode());
        System.out.println(response.getRequestId() + "\n" + response.getData() + "\n"
                + "cost:" + (System.currentTimeMillis() - start));
        return R.ok().put("data", response.getData());
    }
}
