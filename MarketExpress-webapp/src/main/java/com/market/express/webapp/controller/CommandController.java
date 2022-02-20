package com.market.express.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.market.express.webapp.model.Client;
import com.market.express.webapp.model.DeliveryTypes;
import com.market.express.webapp.service.CommandService;
import com.market.express.webapp.shared.Utils;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Controller
public class CommandController {
	
	@Autowired
	CommandService commandService;
	
	@Autowired
	Utils utils;
	
	@PostMapping("/command")
	public ModelAndView addCommand(@ModelAttribute DeliveryTypes deliveryType, Model model, HttpServletRequest request) {
		// get current client 
		Client currentClient = utils.getCurrentClient(request);
		
		// add command 
		currentClient = commandService.addCommand(currentClient.getId(), deliveryType.getId());
		
		return new ModelAndView("redirect:/command");	
	}
	
	@GetMapping("/command")
	public String getCommands(Model model, HttpServletRequest request) {
		// get current client
		model.addAttribute("currentClient", utils.getCurrentClient(request));
		
		return "order";
	}
	
	@GetMapping("/command/delete/{id}")
	public ModelAndView deleteCommands(@PathVariable("id") final Long id, Model model, HttpServletRequest request) {
		// get current client
		Client currentClient = utils.getCurrentClient(request);
		model.addAttribute("currentClient", currentClient);
		
		// delete command id from client commands list
		commandService.deleteCommand(currentClient.getId(), id);
		
		return new ModelAndView("redirect:/command");
	}
	
}
