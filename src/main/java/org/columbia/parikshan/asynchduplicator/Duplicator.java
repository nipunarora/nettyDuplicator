
package org.columbia.parikshan.duplicator;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.columbia.parikshan.Main;

public final class Duplicator {

    public static void execute(int LOCAL_PORT, String REMOTE_HOST, int REMOTE_PORT, String REMOTE_HOST2, int REMOTE_PORT2) throws Exception {
        System.err.println("Proxying *:" + LOCAL_PORT + " to " + REMOTE_HOST + ':' + REMOTE_PORT + " and duplicating to " + REMOTE_HOST2 + ':' + REMOTE_PORT2);


        LoggingHandler l;
        if(Main.debug)
            l = new LoggingHandler(LogLevel.INFO);
        else
            l = new LoggingHandler(LogLevel.DEBUG);

        // Configure the bootstrap.
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .handler(l)
             .childHandler(new DuplicatorProxyInitializer(REMOTE_HOST, REMOTE_PORT,REMOTE_HOST2,REMOTE_PORT2))
             .childOption(ChannelOption.AUTO_READ, false)
             .bind(LOCAL_PORT).sync().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
