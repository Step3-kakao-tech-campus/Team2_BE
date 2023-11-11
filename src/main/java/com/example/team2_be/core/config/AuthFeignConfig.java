package com.example.team2_be.core.config;

import feign.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
public class AuthFeignConfig {

    @Bean
    public Client feignClient() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("krmp-proxy.9rum.cc", 3128));
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        return new Client.Default(sslSocketFactory, new ProxySelectorHostnameVerifier(proxy));
    }

    public static class ProxySelectorHostnameVerifier implements HostnameVerifier {
        private final Proxy proxy;

        public ProxySelectorHostnameVerifier(Proxy proxy) {
            this.proxy = proxy;
        }

        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return true;
        }

        public Proxy getProxy() {
            return this.proxy;
        }
    }
}
