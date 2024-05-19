package edu.swpu;

import edu.swpu.modules.source.dao.DouyinAwemeCommentDao;
import edu.swpu.modules.source.entity.DouyinAwemeCommentEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest(classes = DataApplication.class)
class DataApplicationTests {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private DouyinAwemeCommentDao douyinAwemeCommentDao;

    @BeforeEach
    public void setup() {
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.61.145:9200")
        ));
    }

    //客户端自动销毁
    @AfterEach
    void tearDown() throws IOException {
        this.client.close();
    }
    //批量导入用户数据到es索引库
    @Test
    public void testBulkIndexComments() throws IOException {
        //查全部，后期做个时间戳检测
        List<DouyinAwemeCommentEntity> comments = douyinAwemeCommentDao.selectList(null);

        BulkRequest bulkRequest = new BulkRequest();
        for (DouyinAwemeCommentEntity comment : comments) {
            bulkRequest.add(new IndexRequest("douyin_aweme_comment_index").id(comment.getId().toString())
                    .source(comment, XContentType.JSON));
        }

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setSocketTimeout(60000)
                .build();
        RequestOptions options = RequestOptions.DEFAULT.toBuilder()
                .setRequestConfig(requestConfig)
                .build();


        BulkResponse bulkResponse = client.bulk(bulkRequest, options);
        // 处理导入结果
    }


}
