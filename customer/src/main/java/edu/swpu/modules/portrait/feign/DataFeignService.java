package edu.swpu.modules.portrait.feign;

import edu.swpu.common.utils.R;
import edu.swpu.modules.source.entity.DouyinAwemeCommentEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "ac-data")
public interface DataFeignService {
    //调用模糊查询评论功能
    @RequestMapping("/data/douyinawemecomment/like")
    List<DouyinAwemeCommentEntity> getCommentsByContent(@RequestParam String content);

}
