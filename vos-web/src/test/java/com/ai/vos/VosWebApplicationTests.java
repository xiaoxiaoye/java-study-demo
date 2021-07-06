package com.ai.vos;

import com.ai.vos.component.EtcdTemplate;
import com.netflix.client.ClientFactory;
import com.netflix.client.DefaultLoadBalancerRetryHandler;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.*;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.promises.EtcdResponsePromise;
import mousio.etcd4j.responses.EtcdKeysResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class VosWebApplicationTests {

    @Autowired
    EtcdTemplate etcdTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testBcryptPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String p = encoder.encode("admin");

        System.out.println("encoded password: " + p);

        boolean pass = encoder.matches("123456", "$2a$10$Pcm0srPk/lIMaDZFYe4mkergSWsYHJNvvYKYNBmvgkcKbzf2e7EsS");
        System.out.println("match password:" + pass);
    }


    @Test
    void testEtcdTemplate() {
        List<String> keys = etcdTemplate.listDirKeys("/vos");
        keys.stream().forEach(System.out::println);
    }

    @Test
    void testLoad() {
        try {
            ConfigurationManager.loadCascadedPropertiesFromResources("ribbon-client");
            ILoadBalancer loadBalancer = ClientFactory.getNamedLoadBalancer("MyRibbonClient");
            IRule chooseRule = new RoundRobinRule();
            chooseRule.setLoadBalancer(loadBalancer);
            for (int i = 0; i < 10; i++) {
                Server server = chooseRule.choose(null);
                System.out.println(server.getHostPort());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testLoadBalance() {
        IPing ping = new IPing() {
            @Override
            public boolean isAlive(Server server) {
                RestTemplate restTemplate = new RestTemplate();
                String requestURL = "http://" + server.getHostPort() + "/health";
                String res = restTemplate.getForObject(requestURL, String.class);
                return "true".equals(res);
            }
        };

        List<Server> servers = Arrays.asList(
                new Server("10.1.245.158", 60001),
                new Server("10.1.245.159", 60001)
//                new Server("10.1.245.157", 60001)
        );

        ILoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder()
               .withPing(ping)
                .withRule(new RoundRobinRule())
                .buildFixedServerListLoadBalancer(servers);

        String result = LoadBalancerCommand.<String>builder()
                .withLoadBalancer(loadBalancer)
                .withRetryHandler(new DefaultLoadBalancerRetryHandler(1, 1, true))
                .build().submit(
                        new ServerOperation<String>() {
                            @Override
                            public Observable<String> call(Server server) {
                                try {
                                    RestTemplate restTemplate = new RestTemplate();
                                    String requestURL = "http://" + server.getHostPort() + "/health";
                                    String res = restTemplate.getForObject(requestURL, String.class);
                                    return Observable.just(res);
                                } catch (Exception e) {
                                    return Observable.error(e);
                                }
                            }
                        }
                ).toBlocking().first();
        System.out.println("get result: " + result);
    }


    @Test
    void dynamicServerTest() {
        EtcdClient client = new EtcdClient(
                URI.create("http://10.1.245.157:2389")
        );
        ServerList<Server> serverServerList = new ServerList<Server>() {
            @Override
            public List<Server> getInitialListOfServers() {
                return getUpdatedListOfServers();
            }

            @Override
            public List<Server> getUpdatedListOfServers() {
                List<Server> servers = new ArrayList<>();
                try {
                    EtcdResponsePromise<EtcdKeysResponse> res = client.getDir("/vos/services/registry_manager").send();
                    List<EtcdKeysResponse.EtcdNode> nodes = res.get().getNode().nodes;
                    for (EtcdKeysResponse.EtcdNode node : nodes) {
                        String[] sp = node.key.split("/");
                        String newServerAddress = sp[sp.length - 1];
                        servers.add(new Server(newServerAddress));
                        System.out.println("add new server " + newServerAddress);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return servers;
            }
        };

        IPing ping = new IPing() {
            @Override
            public boolean isAlive(Server server) {
                RestTemplate restTemplate = new RestTemplate();
                String requestURL = "http://" + server.getHostPort() + "/health";
                String res = restTemplate.getForObject(requestURL, String.class);
                return "true".equals(res);
            }
        };
        ILoadBalancer loadBalancer = new DynamicServerListLoadBalancer<Server>(
                new DefaultClientConfigImpl(),
                new RoundRobinRule(),
                ping,
//                new NoOpPing(),
                serverServerList,
                null,
                new PollingServerListUpdater()
        );

        while (true) {
            try {
                Thread.sleep(1000);
                System.out.print("all servers:");
                loadBalancer.getAllServers().stream().forEach(server -> System.out.print("," + server));
                System.out.println();

                System.out.print("reachable servers:");
                loadBalancer.getReachableServers().stream().forEach(server -> System.out.print("," + server));
                System.out.println();
                System.out.println("==================="  + System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
