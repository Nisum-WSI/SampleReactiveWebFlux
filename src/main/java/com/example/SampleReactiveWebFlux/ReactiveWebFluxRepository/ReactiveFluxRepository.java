package com.example.SampleReactiveWebFlux.ReactiveWebFluxRepository;

import org.springframework.data.repository.CrudRepository;

import com.example.SampleReactiveWebFlux.Bo.CustomerProfile;

public interface ReactiveFluxRepository extends CrudRepository<CustomerProfile, Long>{
	
}
