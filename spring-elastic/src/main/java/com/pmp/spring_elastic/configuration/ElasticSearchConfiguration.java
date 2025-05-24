// package com.pmp.spring_elastic.configuration;

// import org.apache.http.Header;
// import org.apache.http.HttpHost;
// import org.apache.http.message.BasicHeader;
// import org.elasticsearch.client.RestClient;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import co.elastic.clients.elasticsearch.ElasticsearchClient;
// import co.elastic.clients.json.jackson.JacksonJsonpMapper;
// import co.elastic.clients.transport.ElasticsearchTransport;
// import co.elastic.clients.transport.rest_client.RestClientTransport;

// @Configuration
// public class ElasticSearchConfiguration {

// @Value("${elastic.host}")
// private String host;

// @Value("${elastic.apiKey}")
// private String apiKey;

// @Bean
// ElasticsearchClient elasticsearchClient() {
// RestClient restClient = RestClient.builder(
// new HttpHost(host, 443, "https")).setDefaultHeaders(
// new Header[] {
// new BasicHeader("api_key", apiKey)
// })
// .build();

// ElasticsearchTransport transport = new RestClientTransport(restClient,
// new JacksonJsonpMapper());

// return new ElasticsearchClient(transport);
// }
// }
