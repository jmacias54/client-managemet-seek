package com.pe.client_management.contract;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;


@RequiredArgsConstructor
@Slf4j
public class ListMapper<From, To> {

	private final Mapper<From, To> mapper;

	public List<To> map(List<From> input) {
		return isNull(input.stream()) || input.isEmpty()
			? new ArrayList<>()
			: input.stream()
			.map(this.mapper::map)
			.collect(Collectors.toList());
	}

}