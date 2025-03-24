package edu.eci.cvds.reservas.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpConfig {
    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> servletContainer() {
        return factory -> {
            if (factory instanceof TomcatServletWebServerFactory) {
                TomcatServletWebServerFactory tomcatFactory = (TomcatServletWebServerFactory) factory;
                tomcatFactory.addAdditionalTomcatConnectors(createHttpConnector());
            }
        };
    }

    private Connector createHttpConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }

}