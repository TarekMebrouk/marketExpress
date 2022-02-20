package com.market.express.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.market.express.webapp.model.Client;
import com.market.express.webapp.service.ClientService;
import com.market.express.webapp.shared.Utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Controller
public class ClientController {

	@Autowired
	ClientService clientService;
	
	@Autowired
	GlobalController globalController;
	
	@Autowired
	Utils utils;
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("currentClient", new Client());
		if (!model.containsAttribute("error")){
			model.addAttribute("error", null);
		}
		return "signin";
	}
	
	@PostMapping("/login")
	public String authenticate(@ModelAttribute Client currentClient, Model model, HttpServletRequest request) {
		Client client = clientService.login(currentClient);
		
		// login failed
		if (client == null) {
			model.addAttribute("currentClient", new Client());
			model.addAttribute("error", "Login failed !, please check your personal credentials and try again ...");
			return "signin";	
		}
		
		// login success
		else {
			request.getSession().setAttribute("client", client.getId());
			return globalController.home(model, request);
		}
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("currentClient", new Client());
		if (!model.containsAttribute("error")){
			model.addAttribute("error", null);
		}
		return "signup";
	}
	
	@PostMapping("/register")
	public String createClient(@ModelAttribute Client currentClient, Model model, HttpServletRequest request) {
		Client client = clientService.register(currentClient);
		
		// registration failed
		if (client == null) {
			model.addAttribute("currentClient", new Client());
			model.addAttribute("error", "SignUp failed !, please check your personal information and try again ...");
			return "signup";	
		}
		
		// registration success
		else {
			request.getSession().setAttribute("client", client.getId());
			return globalController.home(model, request);
		}
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().removeAttribute("client");
		return new ModelAndView("redirect:/");	
	}
	
	@GetMapping("/account")
	public String getAccount(Model model, HttpServletRequest request) {
		model.addAttribute("currentClient", utils.getCurrentClient(request));
		return "profile";
	}
	
	@PostMapping("/account")
	public String editAccount(@ModelAttribute Client currentClient, Model model, HttpServletRequest request) {
		currentClient.setId(utils.getCurrentClient(request).getId());
		Client client = clientService.updateClient(currentClient);
		
		// update client failed
		if (client == null) {
			model.addAttribute("currentClient", new Client());
			model.addAttribute("error", "Client edit failed !, please check your personal information and try again ...");
			return "profile";	
		}
		
		// update client success
		else {
			model.addAttribute("currentClient", utils.getCurrentClient(request));
			return "profile";
		}
	}
	
	@GetMapping("/account/delete")
	public ModelAndView deleteClient(HttpServletRequest request) {
		// get current client to delete
		Client currentClient= utils.getCurrentClient(request);
		clientService.deleteClient(currentClient.getId());
		
		// logout user
		return this.logout(request);	
	}
}
