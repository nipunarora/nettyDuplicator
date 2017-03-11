/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.columbia.parikshan.asynchduplicator;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class DuplicatorProxyInitializer extends ChannelInitializer<SocketChannel> {

    private final String remoteHost;
    private final int remotePort;

    private final String remoteHost2;
    private final int remotePort2;

    public DuplicatorProxyInitializer(String remoteHost, int remotePort, String remoteHost2, int remotePort2) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
        this.remoteHost2 = remoteHost2;
        this.remotePort2 = remotePort2;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        //ch.pipeline().addLast(new HexDumpProxyFrontendHandler(remoteHost,remotePort,remoteHost2,remotePort2));
        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG),new DuplicatorFrontendHandler(remoteHost, remotePort,remoteHost2,remotePort2));
    }
}
