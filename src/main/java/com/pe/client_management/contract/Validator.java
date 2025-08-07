package com.pe.client_management.contract;

public interface Validator<T> {

	boolean isValid(T value);

}
