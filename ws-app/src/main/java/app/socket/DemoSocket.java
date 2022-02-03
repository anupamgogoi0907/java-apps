package app.socket;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Map;

@ServerEndpoint(value = "/socket", configurator = ServletAwareConfigurator.class)
public class DemoSocket {

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        System.out.println("Connected:" + session.getId());
        Map<String, List<String>> headers = (Map<String, List<String>>) config.getUserProperties().get("headers");
        headers.forEach((k, v) -> {
            System.out.println("key:" + k);
        });
    }

    @OnMessage
    public String onMessage(Session session, String msg) {
        System.out.println("Received:" + msg);
        return "Hello " + msg;
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Closing:" + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error");
    }

}
