package com.ls.wedding.controller;

import com.soecode.wxtools.api.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx")
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private IService wxService;

    @GetMapping("/check")
    public String check(String signature, String timestamp, String nonce, String echostr) {
        logger.info("wocao");
        if (wxService.checkSignature(signature, timestamp, nonce, echostr)) {
            return echostr;
        }
        return null;
    }
}