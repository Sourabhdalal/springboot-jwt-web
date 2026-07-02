package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services;


import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.Session;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.User;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final int SESSION_LIMIT = 2;

    public void generateNewSession(User user, String refreshToken)
    {
        List<Session> sessionList = sessionRepository.findByUser(user);
        if(sessionList.size() == SESSION_LIMIT)
        {
             sessionList.sort(Comparator.comparing(Session::getLastUsedAt));

             Session leastRecentlyUsedSession = sessionList.getFirst();
             sessionRepository.delete(leastRecentlyUsedSession);
        }

        Session newSession = Session.builder().
                user(user)
                .refreshToken(refreshToken)
                .build();

        sessionRepository.save(newSession);

    }

    public void validateSession(String refreshToken)
    {
        Session session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException("Session is no longer exist with refresh token"));

        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }
}
