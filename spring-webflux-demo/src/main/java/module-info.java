open module spring.webfluxMain {
    requires jdk.unsupported;
    requires spring.boot;
    requires spring.boot.starter.reactor.netty;
    requires spring.boot.starter.webflux;
    requires reactor.core;
    requires spring.boot.starter;
    requires spring.web;
    requires spring.webflux;
    requires org.reactivestreams;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.core;

    requires java.base;

    requires org.mapstruct;
    requires org.mapstruct.processor;

}