package edu.swpu.modules.source.controller;

import edu.swpu.common.utils.R;
import edu.swpu.modules.analytics.entity.HotTopicsEntity;
import edu.swpu.modules.analytics.service.HotTopicsService;
import edu.swpu.modules.analytics.service.UserBehaviorStatsService;
import edu.swpu.modules.source.entity.RequestDto;
import edu.swpu.modules.source.service.DouyinAwemeCommentService;
import edu.swpu.modules.source.service.DouyinAwemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/data/flask")
public class FlaskController {

    @Autowired
    private HotTopicsService hotTopicsService;

    @Autowired
    private DouyinAwemeService douyinAwemeService;

    @Autowired
    private DouyinAwemeCommentService douyinAwemeCommentService;

    @Autowired
    private UserBehaviorStatsService userBehaviorStatsService;

    private final RestTemplate restTemplate;

    public FlaskController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/ok")
    public R yourEndpoint() {
        // 调用Flask接口
        String flaskUrl = "http://127.0.0.1:5000/hello"; // Flask接口地址
        String flaskResponse = restTemplate.getForObject(flaskUrl, String.class);

        // 在这里可以对Flask返回的数据进行处理，然后返回给前端
        return R.ok().put("data",flaskResponse);
    }

    @PostMapping("/crawl")
    public R callFlaskApi(@RequestBody RequestDto requestDto) {
        // 将参数传递给Flask接口
        String flaskUrl = "http://127.0.0.1:5000/crawl";
        ResponseEntity<String> response = restTemplate.postForEntity(flaskUrl, requestDto, String.class);
        ResponseEntity<String> data = new ResponseEntity<>(response.getBody(), response.getStatusCode());

        if (data.getStatusCodeValue() == 200){
            //爬取成功后，插入热词表记录
            HotTopicsEntity hotTopicsEntity = new HotTopicsEntity();
            hotTopicsEntity.setTopic(requestDto.getKeywords());//设置搜索关键词为热词表话题
            long current_timestamp = System.currentTimeMillis();//获取当前时间戳
            hotTopicsEntity.setLastUpdateTs(current_timestamp);
            //获取新增视频数和评论数
            int videoCount = douyinAwemeService.countNewRecordsWithinOneMinute();
            int commentsCount = douyinAwemeCommentService.countNewRecordsWithinOneMinute();
            hotTopicsEntity.setCommentCount(commentsCount);
            hotTopicsEntity.setVideoCount(videoCount);
            System.out.println("热词表最后更新时间戳："+current_timestamp);
            hotTopicsService.save(hotTopicsEntity);
            //操作用户分析表
            userBehaviorStatsService.autoInsert();
        }
        // 返回Flask接口的响应给前端
        return R.ok().put("data",data);
    }
}
