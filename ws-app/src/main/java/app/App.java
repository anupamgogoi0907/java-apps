package app;

import app.socket.DemoSocket;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.javax.server.config.JavaxWebSocketServletContainerInitializer;

public class App {

    public static void initServer() {
        Server server = null;
        ServerConnector connector = null;
        try {
            server = new Server();
            connector = new ServerConnector(server);
            connector.setPort(9999);
            server.addConnector(connector);

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            server.setHandler(context);
            JavaxWebSocketServletContainerInitializer.configure(context, (servletContext, wsContainer) ->
            {
                wsContainer.addEndpoint(DemoSocket.class);
            });
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        initServer();
    }
}
