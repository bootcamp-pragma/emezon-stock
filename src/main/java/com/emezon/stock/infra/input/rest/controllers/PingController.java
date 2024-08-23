package com.emezon.stock.infra.input.rest.controllers;

import com.emezon.stock.domain.ports.input.IPingInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
@RequiredArgsConstructor
public class PingController {

    private final IPingInputPort pingInputPort;

    @GetMapping
    public String ping() {
        return pingInputPort.ping();
    }

}
