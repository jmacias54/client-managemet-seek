package com.pe.client_management.contract;

public interface Updater<ToUpdate, Input> {

	ToUpdate update(ToUpdate entity, Input data);

}

