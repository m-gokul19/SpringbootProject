package com.test.SpringbootAssessment.TicketController;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.SpringbootAssessment.Ticket.Ticket;
import com.test.SpringbootAssessment.Ticket.TicketService;

@Controller
public class TicketController {
	
	@Autowired
	TicketService ticketService;
	
	
	@RequestMapping("/home")
	public String homepage(Model data) {
		
		List<Ticket> ticket=ticketService.getall();
		
		data.addAttribute("ticket",ticket);
		
		return "showticket";
	}
	
	@PostMapping("/DataAdded")
	public String addData(@RequestParam int id,@RequestParam String TicketTitle,@RequestParam String TicketShortDescription,@RequestParam LocalDate TicketCreatedOn,Model data) {
		
		Ticket tic1=new Ticket(id,TicketTitle,TicketShortDescription,TicketCreatedOn);
		
		ticketService.add(tic1);
		
		List<Ticket> ticket=ticketService.getall();
		
		data.addAttribute("ticket",ticket);
		
		return "showticket";
	}
	
	@RequestMapping("/newticket")
	public String newticket() {
		return "home";
	}

	@GetMapping("/search")
	public String search(@RequestParam String TicketTitle,Model data) {
		
		List<Ticket> search=ticketService.filterByName(TicketTitle);
		
		
		
			if(search.isEmpty() ){
				
				return "norecord";
			}
			else {
				data.addAttribute("ticket",search);
				return "aftersearch";
			}
	
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam int id,Model data) {
		
		Ticket t1= new Ticket(id,"","",null);
		
		ticketService.delete(t1);
		List<Ticket> getall=ticketService.getall();
		
		data.addAttribute("ticket",getall);
		
		return "showticket";
	}
	
		
	@GetMapping("/edit")
	public String edit(@RequestParam int id,Model data) {
		
		Ticket tic=ticketService.findById(id);
		
		ticketService.update(tic);
		
		data.addAttribute("ticket",tic);
		
		return "edit";
	}
	
	@GetMapping("/finishedit")
	public String afterupate(@RequestParam int id,@RequestParam String TicketTitle,@RequestParam String TicketShortDescription,@RequestParam LocalDate TicketCreatedOn,Model data) {
		
		Ticket tic1=new Ticket(id,TicketTitle,TicketShortDescription,TicketCreatedOn);
		ticketService.add(tic1);
		 
		List<Ticket> fin=ticketService.getall();
		data.addAttribute("ticket",fin);
		
		return "showticket";
	}
	@RequestMapping("/view")
	public String viewall(Model data) {
		List<Ticket> tic2=ticketService.getall();
		data.addAttribute("ticket",tic2);
		
		return "view";
	}
	@RequestMapping("/Back")
	public String back() {
		
		return "showticket";
	}
}

// sort level and show
