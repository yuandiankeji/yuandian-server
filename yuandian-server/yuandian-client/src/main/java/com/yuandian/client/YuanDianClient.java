package com.yuandian.client;

import com.yuandian.client.config.ClientConfig;
import com.yuandian.client.net.MessageRegister;
import com.yuandian.client.net.NetConnection;

/**
 * @author twjitm 2019/4/15/22:45
 */
public class YuanDianClient {
    public static void main(String[] args) {
        MessageRegister.register("com.yuandian.test");
        new Thread(new NetConnection(8080, "127.0.0.1")).start();
    }
}
