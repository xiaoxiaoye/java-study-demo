package com.remmy.registrymanager.service;

import com.remmy.registrymanager.entity.ActionLog;

public interface ActionLogService {
    int logAction(ActionLog action);
}
