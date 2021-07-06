package com.ai.vos.common;

import org.springframework.stereotype.Component;

/**
 * @program: vos-web
 * TODO
 * @author: yejx
 * @create: 2020-09-27 15:48
 */

@Component
public class ContextHolder {
    public ThreadLocal<String> CURRENT_THREAD_ID = new ThreadLocal<>();

    public String currentClusterID() {
        return CURRENT_THREAD_ID.get();
    }

    public void setCurrentClusterID(String clusterID) {
        this.CURRENT_THREAD_ID.set(clusterID);
    }
}
