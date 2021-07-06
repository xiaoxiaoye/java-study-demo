package com.remmy.registrymanager.tasks;

import java.util.List;
import java.util.Map;

import com.remmy.registrymanager.entity.Registry;
import com.remmy.registrymanager.service.RegistryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class RegistryJob {
    private static final Logger logger = LoggerFactory.getLogger(RegistryJob.class);

    private final RegistryService registryService;

    private final RestTemplate restTemplate;

    public RegistryJob(RegistryService registryService, RestTemplate rTemplate) {
        this.registryService = registryService;
        this.restTemplate = rTemplate;
    }

    @Scheduled(fixedDelay = 20000)
    public void registryHealthCheck() {
        logger.debug("registry health check");
        List<Registry> registries = registryService.listRegistry(0,0);
        for (Registry registry : registries) {
            StringBuilder builder = new StringBuilder();
            String url = builder.append(registry.getProtocol()).append("://").append(registry.getHost()).append(":")
                    .append(registry.getPort()).append("/v2/_catalog").toString();
            try {
                HttpStatus status = restTemplate.getForEntity(url, Map.class).getStatusCode();
                if (status.is2xxSuccessful()) {
                    logger.debug("registry[{}] is health", registry.getName());
                    registry.setHealth(1);
                } else {
                    logger.warn("registry[{}] is not health", registry.getName());
                    registry.setHealth(0);
                }
            } catch (RestClientException exception) {
                logger.info("registry[{}] is not health, reason:{}", registry.getName(), exception.getMessage());
                registry.setHealth(0);
            }
            registryService.updateRegistry(registry);
        }
    }
}
