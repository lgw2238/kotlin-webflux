package org.lgw.webfluxtoy.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class ApiController {

        @GetMapping( "/getApiData")
        fun getApiData(@RequestParam name : String) : Mono<String> {
            return Mono.just("Data:" + "$name");
        }

}