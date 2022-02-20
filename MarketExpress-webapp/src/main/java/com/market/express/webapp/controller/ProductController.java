package com.market.express.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
public class ProductController {
	
	@Autowired
	ClientService clientService;
	
	@Autowired 
	ProductService productService;
	
	@Autowired
	Utils utils;
	
	@GetMapping("/products")
	public String products(@RequestParam(name = "category", required = false) final String category, 
										 Model model, HttpServletRequest request) {
		// get all products by category
		Iterable<Product> products;
		if (category == null) {
			products = productService.getProducts();
		} else {
			products = productService.getProductsByCategory(category.toLowerCase());
		}
		model.addAttribute("category", category);
		model.addAttribute("products", products);
		model.addAttribute("currentClient", utils.getCurrentClient(request));
		return "products";
	}
	
	@GetMapping("/products/{id}")
	public String singleProduct(@PathVariable("id") final Long id, Model model, HttpServletRequest request) {
		// get product by id
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		model.addAttribute("currentClient", utils.getCurrentClient(request));
		return "single";
	}
	
}
