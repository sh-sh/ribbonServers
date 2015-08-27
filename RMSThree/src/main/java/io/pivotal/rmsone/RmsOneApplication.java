package io.pivotal.rmsone;

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

    @RequestMapping(value = "/", produces = "application/json")
    public String produce() {

        log.info("apple");
        
        return "{\"value\": \"Apple\"}";
    }
    
    public static void main(String[] args) {
        SpringApplication.run(RmsOneApplication.class, args);
    }
}
