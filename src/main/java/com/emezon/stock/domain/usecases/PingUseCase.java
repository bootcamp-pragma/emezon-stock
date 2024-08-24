package com.emezon.stock.domain.usecases;

import com.emezon.stock.domain.ports.input.IPingInputPort;

public class PingUseCase implements IPingInputPort {

    @Override
    public String ping() {
        return "pong";
    }

}
