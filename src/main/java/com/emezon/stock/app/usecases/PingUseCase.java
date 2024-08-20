package com.emezon.stock.app.usecases;

import com.emezon.stock.domain.ports.input.IPingInputPort;
import org.springframework.stereotype.Service;

public class PingUseCase implements IPingInputPort {

    @Override
    public String ping() {
        return "pong";
    }

}
