import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.pdn.Account;

import java.io.IOException;

public class read {
     public static void main(String[] args) {

        RestClient restClient = RestClient
                .builder(HttpHost.create("http://localhost:9200"))
                .build();
         ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
         ElasticsearchClient client = new ElasticsearchClient(transport);

         String searchText = "bike";
         Account account = new Account("bk-1", "City bike", "123.0", "12", "12");
         try {
             SearchResponse<Account> response = client.search(s -> s
                             .index("accounts")
                             .query(q -> q
                                     .match(t -> t
                                             .field("name")
                                             .query(searchText)
                                     )
                             ),
                     Account.class
             );
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
//
//        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
//        ElasticsearchClient client = new ElasticsearchClient(transport);
//
//        Person person = new Person(20, "Mark Doe", new Date(1471466076564L));
//        IndexResponse response = client.index(i -> i
//                .index("person")
//                .id(person.getFullName())
//                .document(person));
//
//        Query ageQuery = RangeQuery.of(r -> r.field("age").from("5").to("15"))._toQuery();
//        SearchResponse<Person> response1 = client.search(s -> s.query(q -> q.bool(b -> b
//                .must(ageQuery))), Person.class);
//        response1.hits().hits().forEach(hit -> log.info("Response 1: {}", hit.source()));
//
    }

}
