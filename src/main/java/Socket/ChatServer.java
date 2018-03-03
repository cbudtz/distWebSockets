package Socket;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ServerEndpoint("/chat/{userid}")
public class ChatServer {
    private static Map<String,Session> sessions = new HashMap<>();
    @OnOpen
    public void onOpen(
            Session session, @PathParam("userid") String id) throws InterruptedException, IOException {
        sessions.put(id, session);
        for (Session s: sessions.values()){
            s.getBasicRemote().sendText("Ny forbindelse: " + id );
        }

    }

    @OnMessage
    public void onMessage(Session session, String msg) throws IOException, InterruptedException {
        for (Map.Entry s: sessions.entrySet()){
            if (s.getValue().equals(session)){ msg = s.getKey() + ": " + msg;
            }
        }
        for (Session s: sessions.values()){
         s.getBasicRemote().sendText(msg);
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException{
        for (Session s : sessions.values()){
            if (Objects.equals(s.getId(), session.getId())){
                sessions.values().remove(session);
            }
        }

    }



}
