package com.maxtrain.bootcamp.invoice;

import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {
	
	Iterable<Invoice> findByStatus(String status);

}
