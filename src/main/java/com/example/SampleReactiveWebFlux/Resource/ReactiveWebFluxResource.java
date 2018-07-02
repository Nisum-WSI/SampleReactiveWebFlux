package com.example.SampleReactiveWebFlux.Resource;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SampleReactiveWebFlux.Bo.CustomerProfile;
import com.example.SampleReactiveWebFlux.Client.SampleReactiveWebFluxClient;
import com.example.SampleReactiveWebFlux.RepositoryService.ReactiveWebFluxService;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ReactiveWebFluxResource {

	@Autowired
	private ReactiveWebFluxService reactiveWebFluxService;

	@Autowired
	private SampleReactiveWebFluxClient sampleReactiveWebFluxClient;
		

	@PostMapping(value="/save_customer_profile")
	Mono<CustomerProfile> saveCustomerProfile(@RequestBody CustomerProfile customerProfile){
		CustomerProfile updateCustomerProfile=	reactiveWebFluxService.saveCustomerProfile(customerProfile);
		return	sampleReactiveWebFluxClient.saveCustomerProfile(updateCustomerProfile);	}

	@GetMapping(produces=MediaType.TEXT_EVENT_STREAM_VALUE ,value="/get_all_Customer_Details")
	Flux<Object> getAllCustomerProfileDetails(){

		Flux<Long> durationTime=Flux.interval(Duration.ofSeconds(1));
		Mono<List<CustomerProfile>> profileData = sampleReactiveWebFluxClient.getAllCustomerProfileDetails();	
		return Flux.zip(profileData, durationTime).map(data -> data.getT1()).repeat().map(n->n);
	}

	@GetMapping(produces=MediaType.TEXT_EVENT_STREAM_VALUE ,value="/get_Customer_Details/{id}")
	Flux<Object> getCustomerProfileDetails(@PathVariable("id") Long id){

		Flux<Long> durationTime=Flux.interval(Duration.ofSeconds(1));
		Mono<CustomerProfile> profileData = sampleReactiveWebFluxClient.getCustomerProfileDetails(id);
		return Flux.zip(profileData, durationTime).map(data -> data.getT1()).repeat().map(n->n);
	}
	
	@DeleteMapping(value="/delete_customer_profile/{id}")
	Mono<Void> deleteCustomerProfile(@PathVariable("id") Long id){		
		return	sampleReactiveWebFluxClient.deleteCustomerProfile(id);
	}
	
	
	@GetMapping(produces=MediaType.TEXT_EVENT_STREAM_VALUE ,value="/get_all_test_Customer_Details")
	Disposable getAllTestCustomerProfileDetails(){

	
		//Flux<Long> durationTime=Flux.interval(Duration.ofSeconds(1));
		return sampleReactiveWebFluxClient.getAllTestCustomerProfileDetails();
		//return Flux.zip(profileData, durationTime).repeat().map(n->n);
	}

}