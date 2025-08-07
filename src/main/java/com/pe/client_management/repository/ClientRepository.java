package com.pe.client_management.repository;


import com.pe.client_management.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	@Query("SELECT AVG(c.age) FROM Client c")
	Double findAverageAge();

	@Query("SELECT STDDEV(c.age) FROM Client c")
	Double findAgeStandardDeviation();
}