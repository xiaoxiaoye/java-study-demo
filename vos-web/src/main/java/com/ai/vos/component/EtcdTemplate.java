package com.ai.vos.component;

import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.promises.EtcdResponsePromise;
import mousio.etcd4j.responses.EtcdKeysResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static mousio.etcd4j.responses.EtcdKeysResponse.EtcdNode;

/**
 * @program: vos-web
 * ETCD访问客户端
 * @author: yejx
 * @create: 2020-09-25 21:55
 */


@Component
public class EtcdTemplate {
    private static final Logger logger = LoggerFactory.getLogger(EtcdTemplate.class);

    private final EtcdClient client;

    public EtcdTemplate(@Value("${vos.etcd.endpoints}") ArrayList<String> endpoints) {
        ArrayList<URI> uris = new ArrayList<>();
        for (String endpoint : endpoints) {
            uris.add(URI.create(endpoint));
        }
        this.client = new EtcdClient(uris.toArray(new URI[]{}));
    }

    public List<String> listDirKeys(String key) {
        ArrayList<String> keys = new ArrayList<>();
        try {
            EtcdResponsePromise<EtcdKeysResponse> res = client.getDir(key).send();
            List<EtcdNode> nodes = res.get().getNode().nodes;
            for (EtcdNode node : nodes) {
                String[] sp = node.key.split("/");
                String childKey = sp[sp.length - 1];
                keys.add(childKey);
            }
        } catch (Exception e) {
            logger.error("etcd list dir failed!", e);
        }
        return keys;
    }
}
