package app.socket;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/socket")
public class DemoSocket {

    @OnOpen
    public void onOpen(Session session){
        System.out.println("Connected:"+session.getId());
    }

    @OnMessage
    public String onMessage(Session session,String msg) {
        System.out.println("Received:" + msg);
        return "Hello " + msg;
    }

    @OnClose
    public void onClose(Session session){
        System.out.println("Closing:"+session.getId());
    }

    @OnError
    public void onError(Session session){
        System.out.println("Error");
    }
}
