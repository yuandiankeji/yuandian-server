package com.yuandian.client.net;

import com.yuandian.client.handler.AbstractRespHandler;

import com.yuandian.data.message.PAuth;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author twjitm 2019/4/15/22:40
 */
public class TcpClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端首次建立网络连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("初始化网络连接");
        SessionManager.getSingleton().setClient(new NetClient(ctx.channel()));
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println(111);
//        PAuth.Builder builder = PAuth.newBuilder();
//        builder.setUid(1L);
//        builder.setDeviceId("111");
//        builder.setToken("1212");
//        byte[] bytes = builder.build().toByteArray();
//
//        SessionManager.getSingleton().getClient().writeData((short) 1002,bytes);

        System.out.println("网络建立完成，可以进行数据传输");
    }

    /**
     * 读取到服务器消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端收到服务器消息");
        ByteBuf byteBuf = (ByteBuf) msg;
        IoMessage ioMessage = MessageCoderFactory.getSingleton().decode(ctx, byteBuf);
        AbstractRespHandler handler = MessageRegister.getHandlerMap(ioMessage.getCmd());
        handler.handler(ioMessage);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
