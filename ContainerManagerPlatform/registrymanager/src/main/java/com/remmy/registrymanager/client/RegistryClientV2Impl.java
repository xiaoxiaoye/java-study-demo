package com.remmy.registrymanager.client;

import java.net.URI;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.remmy.registrymanager.entity.Image;
import com.remmy.registrymanager.entity.Registry;
import com.remmy.registrymanager.service.RegistryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class RegistryClientV2Impl implements RegistryClient {
    private static final Logger logger = LoggerFactory.getLogger(RegistryClientV2Impl.class);

    private final RestTemplate rTemplate;
    private final ObjectMapper objectMapper;
    private final RegistryService registryService;
    private final ThreadPoolTaskExecutor executor;

    private static final String URL_CATALOG = "/_catalog";
    private static final String URL_TAG_LIST = "/tags/list";

    public RegistryClientV2Impl(RestTemplate rTemplate, RegistryService registryService,
            ThreadPoolTaskExecutor executor) {
        this.rTemplate = rTemplate;
        this.registryService = registryService;
        this.executor = executor;
        this.objectMapper = new ObjectMapper();
    }

    public void deleteByTag(String name, String tag) {
        // TODO Auto-generated method stub

    }

    public boolean health() {
        // TODO Auto-generated method stub
        return false;
    }

    public List<Image> listImages(String registryName) throws Exception {
        String registryAddress = getRegistryAddressByName(registryName);
        if (registryAddress == null) {
            throw new RuntimeException("registry is null");
        }

        List<Image> imageList = new LinkedList<>();
        String url = registryAddress + "/v2" + URL_CATALOG;
        String context = rTemplate.getForObject(url, String.class);
        List<String> imageNameList = parseImageNames(context);

        CountDownLatch countDownLatch = new CountDownLatch(imageNameList.size());
        for (String imageName : imageNameList) {
            executor.execute(() -> {
                try {
                    String tagsUrl = registryAddress + "/v2/" + imageName + URL_TAG_LIST;
                    String jsonTagsString = rTemplate.getForObject(tagsUrl, String.class);
                    List<String> tagList = parseImageTags(jsonTagsString);
                    for (String tag : tagList) {
                        Image image = new Image();
                        image.setName(imageName);
                        image.setTag(tag);
                        image.setRegistryAddress(registryAddress);
                        image.setRegistryName(registryName);
                        getImageManifest(registryAddress, image);
                        synchronized (imageList) {
                            imageList.add(image);
                        }
                    }
                } catch (Exception e) {
                    logger.error("get image failed!", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        Collections.sort(imageList, Comparator.comparing(Image::getName));
        return imageList;
    }

    private void getImageManifest(String registryAddress, Image image) throws Exception {
        String manifestUrl = registryAddress + "/v2/" + image.getName() + "/manifests/" + image.getTag();
        LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/vnd.docker.distribution.manifest.v2+json");
        RequestEntity<?> request = RequestEntity.get(manifestUrl)
                .header("Accept", "application/vnd.docker.distribution.manifest.v2+json").build();
        ResponseEntity<String> responseEntity = rTemplate.exchange(request, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String jsonManifestString = responseEntity.getBody();
            ImageManifest manifest = objectMapper.readValue(jsonManifestString, ImageManifest.class);

            String imageId = manifest.getConfig().getDigest().substring(7);
            int imageSize = 0;
            for (Digest digest : manifest.getLayers()) {
                imageSize += digest.getSize();
            }
            image.setImageId(imageId);
            image.setSize(imageSize);
        }
    }

    private String getRegistryAddressByName(String registryName) {
        Registry registry = registryService.getRegistryByName(registryName);
        try {

            URI uri = new URI(registry.getProtocol(), null, registry.getHost(), registry.getPort(), null, null, null);
            return uri.toString();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    private List<String> parseImageNames(String context) {
        List<String> imageNameList = new LinkedList<>();
        try {
            JsonNode jsonNode = objectMapper.readTree(context);
            jsonNode = jsonNode.get("repositories");
            if (jsonNode instanceof NullNode) {
                return imageNameList;
            }
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            for (JsonNode node : arrayNode) {
                imageNameList.add(node.asText());
            }
            return imageNameList;
        } catch (JsonProcessingException e) {
            return Collections.emptyList();
        }
    }

    private List<String> parseImageTags(String context) {
        try {
            List<String> tagList = new LinkedList<>();
            JsonNode jsonNode = objectMapper.readTree(context);
            jsonNode = jsonNode.get("tags");
            if (jsonNode instanceof NullNode) {
                return tagList;
            }
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            for (JsonNode node : arrayNode) {
                tagList.add(node.asText());
            }
            return tagList;
        } catch (JsonProcessingException e) {
            return Collections.emptyList();
        }
    }

    static class Digest {
        private String mediaType;
        private int size;
        private String digest;

        public String getMediaType() {
            return mediaType;
        }

        public void setMediaType(String mediaType) {
            this.mediaType = mediaType;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }
    }

    static class ImageManifest {
        private String schemaVersion;
        private String mediaType;
        private Digest config;
        private List<Digest> layers;

        public String getSchemaVersion() {
            return schemaVersion;
        }

        public void setSchemaVersion(String schemaVersion) {
            this.schemaVersion = schemaVersion;
        }

        public String getMediaType() {
            return mediaType;
        }

        public void setMediaType(String mediaType) {
            this.mediaType = mediaType;
        }

        public Digest getConfig() {
            return config;
        }

        public void setConfig(Digest config) {
            this.config = config;
        }

        public List<Digest> getLayers() {
            return layers;
        }

        public void setLayers(List<Digest> layers) {
            this.layers = layers;
        }
    }
}
