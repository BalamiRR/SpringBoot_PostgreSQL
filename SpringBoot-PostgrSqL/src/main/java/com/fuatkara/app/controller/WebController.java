package com.fuatkara.app.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fuatkara.model.Customer;
import com.fuatkara.repo.CustomerRepository;

@RestController
public class WebController {
	
	@Autowired
	CustomerRepository repository;

	@RequestMapping(value = "/save")
	public String process() {
		// save a single Customer
		repository.save(new Customer("Jack", "Smith"));
		// save a list of Customers
		repository.saveAll(Arrays.asList(new Customer("Adam", "Johnson"), 
				new Customer("Kim", "Smith"),
				new Customer("David", "Williams"), 
				new Customer("Peter", "Davis")));
		return "Done";
	}

	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	public String findAll() {
		String result = "<html>";

		for (Customer cust : repository.findAll()) {
			result += "<div>" + cust.toString() + "<div>";
		}
		return result + "</html>";
	}

	@RequestMapping(value = "/findbyid", method = RequestMethod.GET)
	public String findById(@RequestParam("id") long id) {
		String result = "";
		result = repository.findById(id).toString();
		//Bizim Customer class'inda belirledigimiz toString() gibi sonucu cikar dedmek. 
		return result;
	}

	@RequestMapping(value = "/findbylastname", method = RequestMethod.GET)
	public String fetchDataByLastName(@RequestParam("lastname") String lastName) {
		String result = "<html>";

		for (Customer cust : repository.findByLastName(lastName)) {
			result += "<div>" + cust.toString() + "</div>";
		}

		return result + "</html>";
	}
}
