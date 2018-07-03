package com.example.controller;

import com.example.car.*;
import com.example.beans.Welcome;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
public class WelcomeController {
private static final String welcomemsg = "Welcome Mr. %s!";
    
	@GetMapping("/welcome/useme")
    @ResponseBody
    public Welcome welcomeUser(@RequestParam(name="name", required=false, defaultValue="Java Fan") String name) {
        return new Welcome(String.format(welcomemsg, name));
    }
	
	@RequestMapping(value = "/")
	public ResponseEntity<Car> get() {

	    Car car = new Car();
	    car.setColor("Blue");
	    car.setMiles(100);
	    car.setVIN("1234");

	    return new ResponseEntity<Car>(car, HttpStatus.OK);
	}
    
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Car> update(@RequestBody Car car) {

	    if (car != null) {
	        car.setMiles(car.getMiles() + 100);
	    }

	    // TODO: call persistence layer to update
	    return new ResponseEntity<Car>(car, HttpStatus.OK);
	}
    
}