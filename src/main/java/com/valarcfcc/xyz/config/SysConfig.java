package com.valarcfcc.xyz.config;

import com.valarcfcc.xyz.annotation.ValueXyz;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SysConfig {
    @Value("${application.path.temp}")
    @ValueXyz("application.path.temp")
    private String tempPath;

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

}
