package com.test.SpringbootAssessment.Ticket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;


@Service
public class TicketService {
	
	@Autowired
	TicketRepository repo;
	
	public void add(Ticket ticket) {
		repo.save(ticket);
	}
	
	public  void update(Ticket ticket) {
		repo.save(ticket);
	}
	
	public void delete(Ticket ticket) {
		 repo.delete(ticket);
	}
	
	public Ticket findById(int id) {
		if(repo.findById(id).isEmpty()) {
			return null;
		}
		else {
			return repo.findById(id).get();
		}
	}
	
	public List<Ticket> getall() {
		return repo.findAll();
	}
	
public List<Ticket> filterByName(String searchKey){
		
		//Dummmy
	Ticket dummy=new  Ticket();
		dummy.setTicketTitle(searchKey);
		
		//where with exampleMathcher
		
		ExampleMatcher example=ExampleMatcher.matching().withMatcher("TicketTitle",ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("id","TicketShortDescription","TicketCreatedOn");
			
		//combining dummy with where
		Example<Ticket> example1= Example.of(dummy, example);
		
		//System.out.println(repo.findAll(example1));
		
		return repo.findAll(example1);
	}
}
