package edu.swpu.modules.source.controller;

import edu.swpu.common.utils.R;
import edu.swpu.modules.source.entity.RequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/data/flask")
public class FlaskController {

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
        ResponseEntity<String> entity = new ResponseEntity<>(response.getBody(), response.getStatusCode());
        // 返回Flask接口的响应给前端
        return R.ok().put("data",entity);
    }
}
