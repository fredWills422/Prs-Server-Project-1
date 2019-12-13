package com.maxtrain.bootcamp.prs.lineitem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LineItemRepository extends JpaRepository<LineItem, Integer>{

	Iterable<LineItem> findLineItemByRequestId(Integer requestId);
	List<LineItem> findAllLineItemsByRequestId(Integer requestId);
	
}
