package com.example.demo4.SecurityApp.services;

import com.example.demo4.SecurityApp.entities.SessionEntity;
import com.example.demo4.SecurityApp.entities.UserEntity;
import com.example.demo4.SecurityApp.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Session {

    private final SessionRepository sessionRepository;
    private final int SESSION_LIMIT = 2;

    public void createSession(UserEntity user, String refreshToken){
        List<SessionEntity> userSessions = sessionRepository.findByUser(user);

        if(userSessions.size()==SESSION_LIMIT){
            userSessions.sort(Comparator.comparing(SessionEntity::getLastUsedAt));
            SessionEntity lastUsedSession = userSessions.getFirst();

            sessionRepository.delete(lastUsedSession);

        }

        SessionEntity session = SessionEntity.builder()
                .refreshToken(refreshToken)
                .user(user)
                .build();

        sessionRepository.save(session);


    }

    public void validateSession(String refreshToken){
        SessionEntity session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException("Session does not exists for the refreshToken "+refreshToken));
        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);

    }
}
