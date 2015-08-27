package io.pivotal.rmsone;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
public class RmsOneApplication {
	
	private Log log = LogFactory.getLog(RmsOneApplication.class);
    private AtomicInteger counter = new AtomicInteger(0);

    @RequestMapping(value = "/", produces = "application/json")
    public String produce() {
        int value = counter.incrementAndGet();

        log.info(String.format("Produced a value: %s", value));
        return "{\"value\": \"Ford\"}";
        //return String.format("{\"value\": %s}", value);
    }
    

    public static void main(String[] args) {
        SpringApplication.run(RmsOneApplication.class, args);
    }
}
