package com.javapatterns.state.tcp;

public interface TcpState {
    void open();

    void close();

    void acknowledge();
}
