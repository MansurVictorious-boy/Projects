package com.nt.service;


import com.nt.entity.CartModule;


public interface CartModuleSevice {
	public CartModule addToCart(Long custemerId, Long bookId, int quantity);

	public void deleteToCart(Long id);
}
