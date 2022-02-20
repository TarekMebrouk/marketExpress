package com.market.express.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.market.express.webapp.model.Client;
import com.market.express.webapp.model.Product;
import com.market.express.webapp.service.ClientService;
import com.market.express.webapp.service.ProductService;
import com.market.express.webapp.shared.Utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Controller
public class GlobalController {

	@Autowired
	ClientService clientService;
	
	@Autowired 
	ProductService productService;
	
	@Autowired
	Utils utils;
	
	@GetMapping("/")
	public String home(Model model, HttpServletRequest request) {
		// get all products 
		Iterable<Product> products = productService.getProducts();
		model.addAttribute("products", products);
		model.addAttribute("currentClient", utils.getCurrentClient(request));
		return "home";
	}
	
	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("currentClient", new Client());
		return "contact";
	}
	
}
