package com.ls.wedding.config;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanComponent {

    @Bean("wxService")
    public IService getWxService() {
        return new WxService();
    }
}
