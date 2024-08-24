package com.emezon.stock.app.usecases;

import com.emezon.stock.domain.usecases.PingUseCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PingUseCaseTest {

    @Test
    void testPing() {
        // Arrange
        PingUseCase pingUseCase = new PingUseCase();

        // Act
        String result = pingUseCase.ping();

        // Assert
        assertEquals("pong", result);
    }

}
