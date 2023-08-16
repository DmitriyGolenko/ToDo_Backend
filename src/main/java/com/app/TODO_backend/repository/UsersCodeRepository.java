package com.app.TODO_backend.repository;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
@Log4j2
public class UsersCodeRepository {
    private final Set<String> tokenRep = new HashSet<>();
    private final Random tokenGenerator = new Random();
    public String generateToken(){
        String token =  String.valueOf(tokenGenerator.nextInt(2_000_000, 2_000_000_000));
        //TODO обработка ошибки
        tokenRep.add(token);
        return token;
    }

    public boolean verifyToken(String token) {
        log.warn(tokenRep);
        return tokenRep.remove(token);
    }

}
