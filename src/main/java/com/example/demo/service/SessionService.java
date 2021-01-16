package com.example.demo.service;

import com.example.demo.service.model.Session;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface SessionService {
    Session makeAppointment(UUID service,
                            UUID client, UUID specialist, String date);

    List<Session> getSessionsByClient(UUID client);
    List<Session> getSessionsBySpecialist(UUID specialist);
    String getOrdersInfo(Session session, UUID service,
                         UUID client,
                         UUID specialist);

}
