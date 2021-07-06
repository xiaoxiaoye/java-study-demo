package org.example;

import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.get.GetResult;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yejiaxin
 */
public class ElasticsearchDemo {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.109.6", 9200, "http")
                )
        );

        ElasticsearchDemo demo = new ElasticsearchDemo();
//        demo.indexCreateSync(client);
//        demo.getSync(client);
//        demo.deleteSync(client);
        demo.updateSync(client);

        client.close();
    }

    /**
     * 同步创建index
     * @param client
     * @throws IOException
     */
    public void indexCreateSync(RestHighLevelClient client) throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "kimchy");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch");
        IndexRequest indexRequest = new IndexRequest("posts").id("1").source(jsonMap);
        // 设置超时时间
        indexRequest.timeout(TimeValue.timeValueSeconds(1));
        // 设置操作类型
        indexRequest.opType(DocWriteRequest.OpType.CREATE);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse);
    }

    /**
     * 删除document
     * @param client
     * @throws IOException
     */
    public void deleteSync(RestHighLevelClient client) throws IOException{
        DeleteRequest request = new DeleteRequest("posts", "1");
        request.timeout(TimeValue.timeValueSeconds(1));
        DeleteResponse deleteResponse = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(deleteResponse);
    }

    /**
     * 查询document
     * @param client
     * @throws IOException
     */
    public void getSync(RestHighLevelClient client) throws IOException {
        GetRequest getRequest = new GetRequest("posts", "1");
        // 需要包含的字段
        String[] includes = new String[]{"message", "*Date"};
        // 排除的字段
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        getRequest.fetchSourceContext(fetchSourceContext);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        // 检测是否存在
        if (getResponse.isExists()) {
            Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
            System.out.println(sourceAsMap);
        } else {
            System.out.println("not exist");
        }
        System.out.println(getResponse);
    }

    /**
     * 更新document
     * @param client
     * @throws IOException
     */
    public void updateSync(RestHighLevelClient client) throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("updated", new Date());
        jsonMap.put("reason", "daily update");
        UpdateRequest request = new UpdateRequest("posts", "1").doc(jsonMap);
        request.timeout(TimeValue.timeValueSeconds(1));
        // 和其他操作冲突后的重试次数
        request.retryOnConflict(3);

        // source 字段筛选
        String[] includes = new String[]{"updated", "r*"};
        String[] excludes = Strings.EMPTY_ARRAY;
        request.fetchSource(new FetchSourceContext(true, includes, excludes));

        UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
        System.out.println(updateResponse);

        GetResult result = updateResponse.getGetResult();
        if (result.isExists()) {
            Map<String, Object> sourceAsMap = result.sourceAsMap();
            System.out.println(sourceAsMap);
        } else {
            System.out.println("not exist");
        }
    }
}
