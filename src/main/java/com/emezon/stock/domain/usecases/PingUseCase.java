package com.emezon.stock.domain.usecases;

import com.emezon.stock.domain.api.IPingInputPort;

public class PingUseCase implements IPingInputPort {

    @Override
    public String ping() {
        return "pong";
    }

}
