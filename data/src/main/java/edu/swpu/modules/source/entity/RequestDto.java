package edu.swpu.modules.source.entity;

import lombok.Data;

import java.util.List;

@Data
public class RequestDto {
    //爬取平台
    private String platform;
    //验证方式
    private String lt;
    //爬取类型
    private String type;
    //爬起关键词
    private String keywords;
    //最大爬取帖子数量
    private Integer maxNotesCount;
    //是否爬取评论
    private Boolean enableGetComments;
    //指定抖音视频爬取列表
    private List<String> dySpecifiedIdList;

}
