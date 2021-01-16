package com.example.demo.controller;


import com.example.demo.service.SessionService;
import com.example.demo.service.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class SessionController {
    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping(value = "/clients/{name}/sessions")
    public ResponseEntity<Session> makeAppointment(@RequestParam UUID service,
                                                   @RequestParam UUID client,
                                                   @RequestParam UUID specialist,
                                                   @RequestParam String date) {
        return ResponseEntity.ok(sessionService.makeAppointment(service,
                client,
                specialist,
                date));
    }


    @GetMapping(value = "/sessions")
    public ResponseEntity<String> getSessionInfobyClient(@RequestParam UUID client) {
        List<Session> sessions = sessionService.getSessionsByClient(client);
        String info = "";
        for(Session session : sessions) {
            info += sessionService.getOrdersInfo(session, session.getService(),
                    session.getClient(),
                    session.getSpecialist());
        }
        return ResponseEntity.ok(info);
    }

    @GetMapping(value = "/specialists")
    public ResponseEntity<String> getSessionInfobySpecialist(@RequestParam UUID specialist) {
        List<Session> sessions = sessionService.getSessionsBySpecialist(specialist);
        String info = "";
        for(Session session : sessions) {
            info += sessionService.getOrdersInfo(session, session.getService(),
                    session.getClient(),
                    session.getSpecialist());
        }
        return ResponseEntity.ok(info);
    }
}