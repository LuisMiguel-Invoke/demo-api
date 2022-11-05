package cl.cclh.demo.apirestcliente.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cl.cclh.demo.apirestcliente.model.Cliente;

@Repository
public interface IClienteRepo extends MongoRepository<Cliente, String>{
	Optional<Cliente> findByRut(int rut);
	void deleteByRut(int rut);
}
