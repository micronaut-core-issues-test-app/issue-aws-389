package com.example;

import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller
public class PingController {

    @Get(value = "/ping", produces = MediaType.TEXT_PLAIN)
    public String pingV1() {
        System.out.println("Received a ping v1");
        return "pong v1";
    }

    @Version("2")
    @Get(value = "/ping", produces = MediaType.TEXT_PLAIN)
    public String pingV2() {
        System.out.println("Received a ping v2");
        return "pong v2";
    }
}
