package org.example.vetalisbackend.iam.infrastructure.hashing;

import org.example.vetalisbackend.iam.application.internal.outboundservices.HashingService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptHashingService implements HashingService {

    private final BCryptPasswordEncoder encoder;

    public BCryptHashingService() {
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
