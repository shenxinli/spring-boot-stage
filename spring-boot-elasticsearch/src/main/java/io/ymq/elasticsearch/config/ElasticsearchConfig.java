package io.ymq.elasticsearch.config;


import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * 描述:
 *
 * @author yanpenglei
 * @create 2017-11-01 17:19
 **/
@Configuration
@EnableElasticsearchRepositories(basePackages = "io.ymq.elasticsearch")
public class ElasticsearchConfig {
    /**
     * elk集群地址
     */
    @Value("${elasticsearch.ip}")
    private String hostName;
    /**
     * 端口
     */
    @Value("${elasticsearch.port}")
    private String port;
    /**
     * 集群名称
     */
    @Value("${elasticsearch.cluster.name}")
    private String clusterName;

    /**
     * 连接池
     */
    @Value("${elasticsearch.pool}")
    private String poolSize;

    @Bean
    public TransportClient client() throws Exception {

        Settings esSettings = Settings.builder()
        		.put("cluster.name", clusterName)
                .put("thread_pool.search.size", Integer.parseInt(poolSize))//增加线程池个数，暂时设为5
                .put("xpack.security.user", "elastic:changeme")
                .build();
        return new PreBuiltXPackTransportClient(esSettings)
        				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostName), Integer.parseInt(port)));
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }


}
