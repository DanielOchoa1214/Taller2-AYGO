package edu.aygo.taller2.group;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ObjectMessage;
import org.jgroups.Receiver;
import org.jgroups.View;
import org.jgroups.util.Util;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Getter
@Slf4j
public class StateHandler implements Receiver {
    private JChannel channel;
    private String user_name = System.getProperty("user.name", "n/a");

    public final Map<String, String> users = new ConcurrentHashMap<>();

    @PostConstruct
    public void start() throws Exception {
        channel = new JChannel();
        channel.setReceiver(this);
        channel.connect("MapCluster");
        channel.getState(null, 10000);
        System.out.println("Joined cluster: " + channel.getAddress());
    }

    @PreDestroy
    public void stop() {
        if (channel != null)
            channel.close();
    }

    public Map<String, String> sendMessage(String user) throws Exception {
        Message msg = new ObjectMessage(null, user);
        channel.send(msg);
        return users;
    }

    @Override
    public void viewAccepted(View new_view) {
        System.out.println("** view: " + new_view);
    }

    @Override
    public void receive(Message msg) {
        log.info("Received Message: {} From: {}", msg.getObject(),  msg.getSrc());
        String userName = msg.getObject();
        synchronized (users) {
            users.put(LocalDateTime.now().toString(), userName);
        }
    }

    @Override
    public void getState(OutputStream output) throws Exception {
        synchronized (users) {
            Util.objectToStream(users, new DataOutputStream(output));
        }
    }

    @Override
    public void setState(InputStream input) throws Exception {
        Map<String, String> newUsers = Util.objectFromStream(new DataInputStream(input));
        synchronized (users) {
            users.clear();
            users.putAll(newUsers);
        }
        log.info("{} users have been updated",  users.size());
    }
}
