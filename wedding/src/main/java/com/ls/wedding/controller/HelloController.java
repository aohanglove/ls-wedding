package com.ls.wedding.controller;

import com.ls.wedding.dao.CongratulationDao;
import com.ls.wedding.enums.MsgType;
import com.ls.wedding.model.Congratulation;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.bean.WxUserList.WxUser;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutTextMessage;
import com.soecode.wxtools.exception.WxErrorException;
import com.soecode.wxtools.util.xml.XStreamTransformer;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx")
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private IService wxService;
    @Autowired
    private CongratulationDao congratulationDao;

    @GetMapping("/check")
    public String check(String signature, String timestamp, String nonce, String echostr) {
        if (wxService.checkSignature(signature, timestamp, nonce, echostr)) {
            return echostr;
        }
        return null;
    }

    @PostMapping("check")
    public String checkMsg(HttpServletRequest request, HttpServletResponse response) {
        WxXmlMessage message = null;
        WxXmlOutTextMessage outTextMessage = new WxXmlOutTextMessage();
        try {
            message = XStreamTransformer.fromXml(WxXmlMessage.class, request.getInputStream());
        } catch (IOException e) {
            logger.error("wx message xml parse error", e);
            return null;
        }

        outTextMessage.setFromUserName();
        if (MsgType.TEXT.toString().toLowerCase().equals(message.getMsgType())) {
            String openId = message.getFromUserName();
            try {
                WxUser wxUser = wxService.getUserInfoByOpenId(new WxUserGet(openId, "zh_CN"));
                congratulationDao.insert(message.getContent(), openId, wxUser.getNickname(), wxUser.getHeadimgurl());
            } catch (WxErrorException e) {
                logger.error("wx message insert error", e);

            }
        }
    }

    @GetMapping("/list")
    public Congratulation list() {
        return congratulationDao.findList();
    }
}