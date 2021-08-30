package com.nexsoft.poc.monitoring;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Duration;


/**
 * for access registry of prometheus  http://localhost:8080/actuator/prometheus
 * sedanngkan untuk melihat custom nya
 * @see MonitoringController for sample of monitoring controller
 * @see Application.yml for configuration
 *
 * @link https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.enabling
 */
@SpringBootApplication
public class MonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitoringApplication.class, args);
    }


    /**
     * with MeterRegister , we use current MeterRegistry of springboot actuator
     * assume our SLA within two hours
     * @param meterRegistry current meterRegistry of springboot
     */
    @Bean(name="schedulerSlaTimer")
    public Timer schedulerSlaTimer(MeterRegistry meterRegistry){
        return Timer.builder("schedule_jobs").sla(Duration.ofHours(2)).description("scheduler jobs timer ").register(meterRegistry);
    }



}
