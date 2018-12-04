package com.qinlsspringboot.service.impl;

import com.qinlsspringboot.anno.AdminOnly;
import jdk.nashorn.internal.runtime.Context;
import jdk.nashorn.internal.runtime.logging.DebugLogger;
import jdk.nashorn.internal.runtime.logging.Loggable;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/6/26.
 */
@Component
public class LogService {

    @AdminOnly
    public void log() {
        System.out.println("log from LogSservice");
    }

    public long getLogId(Long logId) {
        System.out.println("get logId return long");
        return logId;
    }

    public void annoArg(String str) {
        System.out.println("execute log service annoArg");
    }

}
