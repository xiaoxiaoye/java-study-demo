package com.ai.vos.config;

import com.ai.vos.component.EtcdTemplate;
import com.netflix.loadbalancer.*;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: vos-web 负载均衡配置
 * @author: yejx
 * @create: 2020-09-25 15:10
 */
@Configuration
public class RibbonClientConfig {
    public static class ClusterServer extends Server {
        private String clusterId;

        public ClusterServer(String id, String clusterID) {
            super(id);
            clusterId = clusterID;
        }

        public String getClusterID() {
            return clusterId;
        }
    }

    private static List<Server> getServerList(EtcdTemplate etcdTemplate) {
        List<Server> servers = new ArrayList<>();
        try {
            List<String> urls = etcdTemplate.listDirKeys("/vos/services/registry_manager");
            for (String url : urls) {
                servers.add(new Server(url));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return servers;
    }

    /**
     * ribbon负载均衡默认配置
     */
    @RibbonClients(defaultConfiguration = DefaultConfig.class)
    protected static class DefaultConfig {
        @Bean
        public IRule rule() {
            return new RoundRobinRule();
        }

        @Bean
        public IPing ping() {
            return new IPing() {
                @Override
                public boolean isAlive(Server server) {
                    RestTemplate restTemplate = new RestTemplate();
                    String requestURL = "http://" + server.getHostPort() + "/health";
                    String res = restTemplate.getForObject(requestURL, String.class);
                    return "true".equals(res);
                }
            };
        }
    }


    @RibbonClient(name = "registry", configuration = RegistryRibbonConfig.class)
    protected static class RegistryRibbonConfig {

        @Bean
        public ServerList<Server> registryServerList(EtcdTemplate etcdTemplate) {
            return new ServerList<Server>() {
                @Override
                public List<Server> getInitialListOfServers() {
                    return getUpdatedListOfServers();
                }

                @Override
                public List<Server> getUpdatedListOfServers() {
                    return RibbonClientConfig.getServerList(etcdTemplate);
                }
            };
        }
    }
}


