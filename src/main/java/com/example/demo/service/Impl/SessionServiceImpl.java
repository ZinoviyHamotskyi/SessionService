package com.example.demo.service.Impl;

import com.example.demo.repo.SessionRepo;
import com.example.demo.service.SessionService;
import com.example.demo.service.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService {
    private final SessionRepo sessionRepo;

    @Autowired
    public SessionServiceImpl(SessionRepo sessionRepo){
        this.sessionRepo = sessionRepo;
    }


    @Override
    public Session makeAppointment(UUID service, UUID client,
                                   UUID specialist, String date) {
        Session session = new Session(service, client,
                specialist, date);
        sessionRepo.save(session);
        return session;
    }

    @Override
    public List<Session> getSessionsBySpecialist(UUID specialist) {
        return sessionRepo.getSessionsBySpecialist(specialist);
    }

    @Override
    public String getOrdersInfo(Session session, UUID service,
                                UUID client, UUID specialist) {
        return "\nSession ID: " + session.getId()
                + "\nService name: " + service
                + "\nClient name: " + client
                + "\nSpecialist name: " + specialist
                + "\nDate: " + session.getDate()
                + "\nPrice: " + service;

    }

    @Override
    public List<Session> getSessionsByClient(UUID client){
        System.out.println(sessionRepo.getSessionsByClient(client).stream().findFirst().toString());
        List<Session> sessions = sessionRepo.getSessionsByClient(client);
        return sessions;
    }
}
