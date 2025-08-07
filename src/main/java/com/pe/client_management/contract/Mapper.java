package com.pe.client_management.contract;

public interface Mapper<From, To> {

	To map(From input);

}


