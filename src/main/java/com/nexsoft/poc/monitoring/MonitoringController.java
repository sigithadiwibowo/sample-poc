package com.nexsoft.poc.monitoring;


import io.micrometer.core.instrument.Timer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.metrics.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;


@RestController
@Slf4j
@AllArgsConstructor
public class MonitoringController {


    /**
     * Existing MeterRegistry in springboot
     */

    private final MeterRegistry meterRegistry;


    /**
     *
     * @see MonitoringApplication bean setup at MonitoringApplication
     */
    private final Timer schedulerSlaTimer ;


    @PostMapping("/counter")
    public Mono<String> counter(){
        log.info("Counter sample hit");
        meterRegistry.counter("counter_test").increment();
        return Mono.just("counter");
    }


    /**
     * record as how many hour it will takes
     */
    @PostMapping("/sla")
    public Mono<String> sla(){
        log.info("Sla sample hit");
        schedulerSlaTimer.record(Duration.ofDays(2));
        return Mono.just("sla");
    }







}
