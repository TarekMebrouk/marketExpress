package com.market.express.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.market.express.webapp.model.Client;
import com.market.express.webapp.model.DeliveryTypes;
import com.market.express.webapp.model.Product;
import com.market.express.webapp.service.CartService;
import com.market.express.webapp.shared.Utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Controller
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	Utils utils;
	
	@GetMapping("/cart")
	public String getClientCart(Model model, HttpServletRequest request) {
		// get current client
		Client currentClient = utils.getCurrentClient(request);
		model.addAttribute("currentClient", currentClient);
		
		// calculate total price 
		float totalPrice = 0;
		for (Product product: currentClient.getCart()) {
			totalPrice += product.getPrice();
		}
		model.addAttribute("totalPrice", String.format("%.2f", totalPrice));
		
		// extract list of delivery types possible
		List<DeliveryTypes> deliverychoices = utils.getPossibleDeliveryTypes(currentClient);
		model.addAttribute("deliverychoices", deliverychoices);
		model.addAttribute("deliveryType", new DeliveryTypes());
		
		return "cart";	
	}
	
	@GetMapping("/cart/add/{productId}")
	public ModelAndView addProductCart(@PathVariable("productId") final Long productId, HttpServletRequest request) {
		// get current client 
		Client currentClient = utils.getCurrentClient(request);
	
		// add product to client cart
		currentClient = cartService.addProductCart(currentClient.getId(), productId);
		
		return new ModelAndView("redirect:/products/"+productId);	
	}
	
	@GetMapping("/cart/delete/{productId}")
	public ModelAndView deleteProductCart(@PathVariable("productId") final Long productId, HttpServletRequest request) {
		// get current client 
		Client currentClient = utils.getCurrentClient(request);
	
		// delete product from client cart
		currentClient = cartService.deleteProductCart(currentClient.getId(), productId);
		
		return new ModelAndView("redirect:/cart");	
	}
}
