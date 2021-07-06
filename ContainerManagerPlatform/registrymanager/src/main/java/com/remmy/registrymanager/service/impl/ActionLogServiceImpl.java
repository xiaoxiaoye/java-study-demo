package com.remmy.registrymanager.service.impl;

import java.util.Date;

import com.remmy.registrymanager.entity.ActionLog;
import com.remmy.registrymanager.repository.mapper.SysLogMapper;
import com.remmy.registrymanager.repository.model.SysLog;
import com.remmy.registrymanager.service.ActionLogService;

import org.springframework.stereotype.Service;

@Service
public class ActionLogServiceImpl implements ActionLogService {
    private final SysLogMapper mapper;

    
    public ActionLogServiceImpl(SysLogMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int logAction(ActionLog action) {
        SysLog sysLog = new SysLog();
        sysLog.setMethod(action.getMethod());
        sysLog.setParams(action.getParams());
        sysLog.setOperation(action.getOperation());
        sysLog.setIp(action.getIp());
        sysLog.setUsername(action.getUsername());
        sysLog.setCreateTime(new Date());
        return mapper.insertSelective(sysLog);
    }
}