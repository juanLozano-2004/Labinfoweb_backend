package edu.eci.cvds.reservas.config;

        import org.apache.catalina.connector.Connector;
        import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
        import org.springframework.boot.web.server.WebServerFactoryCustomizer;
        import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

        /**
         * Configuration class for setting up HTTP to HTTPS redirection.
         */
        @Configuration
        public class HttpConfig {

            /**
             * Customizes the embedded servlet container to add an additional HTTP connector.
             *
             * @return a WebServerFactoryCustomizer that adds an HTTP connector to the Tomcat servlet container.
             */
            @Bean
            public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> servletContainer() {
                return factory -> {
                    if (factory instanceof TomcatServletWebServerFactory) {
                        TomcatServletWebServerFactory tomcatFactory = (TomcatServletWebServerFactory) factory;
                        tomcatFactory.addAdditionalTomcatConnectors(createHttpConnector());
                    }
                };
            }

            /**
             * Creates an HTTP connector that redirects to HTTPS.
             *
             * @return a Connector configured for HTTP with a redirect to HTTPS.
             */
            private Connector createHttpConnector() {
                Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
                connector.setScheme("http");
                connector.setPort(8080);
                connector.setSecure(false);
                connector.setRedirectPort(8443);
                return connector;
            }
        }