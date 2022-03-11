package com.maxtrain.bootcamp.invoiceline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/invoicelines")
public class InvoicelineController {
	
	@Autowired
	private InvoicelineRepository inlnRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Invoiceline>> getInvoicelines() {
		var invoicelines = inlnRepo.findAll();
		return new ResponseEntity<Iterable<Invoiceline>>(invoicelines, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Invoiceline> getInvoiceline(@PathVariable int id) {
		var invoiceline = inlnRepo.findById(id);
		if(invoiceline.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Invoiceline>(invoiceline.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Invoiceline> postInvoiceline(@RequestBody Invoiceline invoiceline) {
		if(invoiceline == null || invoiceline.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var inln = inlnRepo.save(invoiceline);
		return new ResponseEntity<Invoiceline>(inln, HttpStatus.CREATED);
	}
	
//	@SuppressWarnings("rawtypes")
//	@PutMapping("recalculate/{invoiceId}")
//	private ResponseEntity recalculateInvoice(@PathVariable int invoiceId) {
//		var invoice = inlnRepo.findById(invoiceId);
//		
//	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putInvoiceline(@PathVariable int id, @RequestBody Invoiceline invoiceline) {
		if(invoiceline == null || invoiceline.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var inln =inlnRepo.findById(invoiceline.getId());
		if(inln.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		inlnRepo.save(invoiceline);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity putInvoiceline(@PathVariable int id) {
		var invoiceline = inlnRepo.findById(id);
		if(invoiceline.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		inlnRepo.delete(invoiceline.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
