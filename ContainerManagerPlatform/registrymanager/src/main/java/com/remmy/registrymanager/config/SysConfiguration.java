package com.remmy.registrymanager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "registrymanager")
public class SysConfiguration {
    private boolean isLogAction;

    public boolean isLogAction() {
        return isLogAction;
    }

    public void setLogAction(boolean isLogAction) {
        this.isLogAction = isLogAction;
    }
}
