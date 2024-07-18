package org.example.demo;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SomeService {
    @Transactional
    public void throwException() {
        throw new RuntimeException("Some Exception");
    }
}
