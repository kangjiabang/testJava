package db.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.commons.math.stat.descriptive.summary.Product;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

/**
 * @Author  kangjiabang
 * @CreateTime 2022-06-24  14:55
 * @Description TODO
 */
public class ESClient {


    public static void main(String[] args) throws IOException {
        // Create the low-level client
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 9200)).build();

// Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

// And create the API client
        ElasticsearchClient client = new ElasticsearchClient(transport);


        SearchResponse<Product> search = client.search(s -> s
                        .index("products")
                        .query(q -> q
                                .term(t -> t
                                        .field("name")
                                        .value(v -> v.stringValue("bicycle"))
                                )),
                Product.class);

        for (Hit<Product> hit: search.hits().hits()) {
            processProduct(hit.source());
        }
    }

    private static void processProduct(Product source) {
    }
}
