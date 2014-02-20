package com.engagepoint.university.messaging.services;

import com.engagepoint.university.messaging.smpp.ServerMain;
import com.engagepoint.university.messaging.smtp.SMTPMessageHandlerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.subethamail.smtp.server.SMTPServer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ApplicationScoped
public class InitService implements Serializable, Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(InitService.class);

    @Inject
    private ServerMain serverMain;

    @Inject
    private SMTPMessageHandlerFactory emailFactory;

    @PostConstruct
    void init() {
        Thread thread = new Thread(this, "SubeThaSMTP");
        thread.start();
        try {
            serverMain.startSmppServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        SMTPServer server = new SMTPServer(emailFactory);
        server.setPort(25000);
        server.start();
    }
}