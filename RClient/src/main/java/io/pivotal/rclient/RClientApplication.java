package io.pivotal.rclient;

import java.net.URI;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@RestController
public class RClientApplication {

	private Log log = LogFactory.getLog(RClientApplication.class);
	
	@Autowired
	LoadBalancerClient lB;
	
	@RequestMapping("/")
	public String consume() {
		
		ServiceInstance instance = lB.choose("producer");
		URI msUri = URI.create(String.format("http://%s:%s",  instance.getHost(), instance.getPort()));
		
		log.info(msUri.toString());
		
		RestTemplate rT = new RestTemplate();
		
		ProducerResponse pR = rT.getForObject(msUri, ProducerResponse.class);
		
		return String.format("{\"value\": %s}", pR.getValue());
		
	}
	
    public static void main(String[] args) {
        SpringApplication.run(RClientApplication.class, args);
    }
}
