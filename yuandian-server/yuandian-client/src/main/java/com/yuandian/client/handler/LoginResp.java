package com.yuandian.client.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import com.yuandian.client.net.IoMessage;
import com.yuandian.client.net.SessionManager;
import com.yuandian.client.config.ClientConfig;
import com.yuandian.data.message.PLogin;
import com.yuandian.data.message.PSendChat;

/**
 * @author twjitm 2019/4/15/23:15
 */
public class LoginResp extends AbstractRespHandler {

    public LoginResp() {
        super(3);
    }

    @Override
    public void handler(IoMessage message) {
        byte[] bytes = message.getBytes();
        //Todo 你的业务逻辑

      try {
            long toUId = ClientConfig.getSingleton().getToUId();
            PLogin pLogin = PLogin.parseFrom(message.getBytes());
           long uid = pLogin.getUid();
          int i = 0;
           //聊天
          while (true) {
              PSendChat.Builder builder = PSendChat.newBuilder();
               builder.setToUid(uid);
               builder.setUid(uid);
               builder.setContext("你好呀，" + toUId + "小老弟, 我是" + uid + "第" + i + "条消息");
               i++;
               SessionManager.getSingleton().getClient().writeData((short)4,builder.build().toByteArray());
               Thread.sleep(1000);
        }
    } catch (InvalidProtocolBufferException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
