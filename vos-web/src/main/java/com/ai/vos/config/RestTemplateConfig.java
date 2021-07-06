package com.ai.vos.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @program: vos-web
 * TODO
 * @author: yejx
 * @create: 2020-09-23 15:24
 */

@Component
public class RestTemplateConfig implements RestTemplateCustomizer {
    private static final String DEFAULT_CHARSET = "UTF-8";

    @Override
    public void customize(RestTemplate restTemplate) {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory() {
            @Override
            public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
                ClientHttpRequest clientHttpRequest = super.createRequest(uri, httpMethod);
                clientHttpRequest.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                return clientHttpRequest;
            }
        };

        requestFactory.setConnectionRequestTimeout(1000);
        restTemplate.setRequestFactory(requestFactory);

        List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setDefaultCharset(Charset.forName(DEFAULT_CHARSET));
        list.add(0, converter);

        for (HttpMessageConverter<?> httpMessageConverter : list) {
            if (httpMessageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName(DEFAULT_CHARSET));
            }
        }

        restTemplate.setMessageConverters(list);
    }
}
