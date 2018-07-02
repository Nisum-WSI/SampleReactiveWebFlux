package com.example.SampleReactiveWebFlux.Client;

import java.time.Duration;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.SampleReactiveWebFlux.Bo.CustomerProfile;

import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class SampleReactiveWebFluxClient {

	private WebClient webClient = WebClient.create("http://localhost:8080");

	public Mono<CustomerProfile> saveCustomerProfile(CustomerProfile profile){
		return webClient.post()
				.uri("/save_customer_profile_insterling")
				.body(Mono.just(profile), CustomerProfile.class)
				.retrieve()
				.bodyToMono(CustomerProfile.class);
		}
	

	public Mono<List<CustomerProfile>> getAllCustomerProfileDetails() {
		return   webClient.get()
				.uri("/get_All_Customer_Details_insterling")
				.retrieve()
				.bodyToFlux(CustomerProfile.class).collectList();
	}

	public Mono<CustomerProfile> getCustomerProfileDetails(Long id) {
		
		return   webClient.get()
				.uri("/get_Customer_Details_insterling/{id}",id)
				.retrieve()
				.bodyToMono(CustomerProfile.class);	
	}
	
	public Mono<Void> deleteCustomerProfile(Long id){
		return webClient.delete()				
				.uri("/delete_Customer_Details_insterling/{id}",id)
				.retrieve()
				.bodyToMono(Void.class);	
	}
	
	
/*	public Mono<List<CustomerProfile>> getAllCustomerProfileDetails() {
		return   (Mono<List<CustomerProfile>>) webClient.get()
				.uri("/get_All_Customer_Details_insterling")
				 .exchange()
			        .flatMapMany(response -> response.bodyToFlux(CustomerProfile.class))
			        .subscribe();
	}
*/
	
	
	
	
	public Disposable getAllTestCustomerProfileDetails() {
		return   webClient.get()
				.uri("/get_All_Customer_Details_insterling")
				.accept(MediaType.APPLICATION_STREAM_JSON)
		        .exchange()
		        .publishOn(Schedulers.single())
		        .flatMapMany(response -> response.bodyToFlux(CustomerProfile.class))
		        .delayElements(Duration.ofMillis(1000))
		        .subscribe();
	}


}
