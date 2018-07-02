package com.example.SampleReactiveWebFlux.RepositoryService;

import org.springframework.stereotype.Service;

import com.example.SampleReactiveWebFlux.Bo.CustomerProfile;

@Service
public class ReactiveWebFluxService {

	public CustomerProfile saveCustomerProfile(CustomerProfile customerProfile){

		//do the validation and customization
		
		return customerProfile ;
	}

	public void getAllCustomerProfile(){

		//do the validation and customization
		
		
	}


}
