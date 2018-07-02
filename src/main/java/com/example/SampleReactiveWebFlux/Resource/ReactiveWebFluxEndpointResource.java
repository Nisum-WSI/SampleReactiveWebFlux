package com.example.SampleReactiveWebFlux.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SampleReactiveWebFlux.Bo.CustomerProfile;
import com.example.SampleReactiveWebFlux.ReactiveWebFluxRepository.ReactiveFluxRepository;

@RestController
public class ReactiveWebFluxEndpointResource {

	@Autowired
	private ReactiveFluxRepository reactiveFluxRepository;

	@PostMapping(value="/save_customer_profile_insterling")
	public 	CustomerProfile saveCustomerProfile(@RequestBody CustomerProfile customerProfile){

		reactiveFluxRepository.save(customerProfile);
		return customerProfile;
	}

	@GetMapping(value="/get_All_Customer_Details_insterling")
	public 	List<CustomerProfile> getAllCustomerDetails(){	

		List<CustomerProfile> customerProfileList=  new ArrayList<>();
		reactiveFluxRepository.findAll().forEach(customerProfileList::add);
		return customerProfileList;
	}

	@GetMapping(value="/get_Customer_Details_insterling/{id}")
	public 	Optional<CustomerProfile> getCustomerDetails(@PathVariable("id") Long id){			
		return reactiveFluxRepository.findById(id);
	}

	@DeleteMapping(value="/delete_Customer_Details_insterling/{id}")
	public void deleteCustomerDetails(@PathVariable("id") Long id){			
		reactiveFluxRepository.deleteById(id);
	}

	/*@GetMapping(value="/get_All_Customer_Details_insterling")
	public 	List<CustomerProfile> getAllTestCustomerDetails(){	

		List<CustomerProfile> customerProfileList=  new ArrayList<>();
		reactiveFluxRepository.findAll().forEach(customerProfileList::add);
		return customerProfileList;
	}
*/
}
