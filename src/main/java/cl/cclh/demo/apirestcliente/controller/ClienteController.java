package cl.cclh.demo.apirestcliente.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.cclh.demo.apirestcliente.exception.ModeloNotFoundException;
import cl.cclh.demo.apirestcliente.model.Cliente;
import cl.cclh.demo.apirestcliente.repository.IClienteRepo;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	IClienteRepo clienteRepo;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {
		List<Cliente> listaClientes = clienteRepo.findAll();
		return new ResponseEntity<List<Cliente>>(listaClientes, HttpStatus.OK);
	}
	
	@GetMapping("/{rut}")
	public ResponseEntity<Cliente> listarPorId(@PathVariable("rut") int rut) {
		Optional<Cliente> objeto = clienteRepo.findByRut(rut);
		if (objeto.isPresent()) {
		    return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
		} else {
			throw new ModeloNotFoundException("Cliente no encontrado con Rut: " + rut);
		}
	}
	
	@PostMapping
	public ResponseEntity<Cliente> registrar(@Valid @RequestBody Cliente cliente) {
			Cliente objeto = clienteRepo.save(cliente);
		    return new ResponseEntity<>(objeto, HttpStatus.CREATED);	
	}
	
	@RequestMapping(produces = "application/json", method=RequestMethod.PUT)
	public ResponseEntity<Cliente> modificar(@Valid @RequestBody Cliente cliente) {
		Optional<Cliente> data = clienteRepo.findByRut(cliente.getRut());
		if (data.isPresent()) {
			Cliente objeto = data.get();
			objeto.setDv(cliente.getDv());
			objeto.setNombres(cliente.getNombres());
			objeto.setApellidos(cliente.getApellidos());
			objeto.setDomicilio(cliente.getDomicilio());
			objeto.setTelefono(cliente.getTelefono());
			objeto.setEmail(cliente.getEmail());
			objeto.setSexo(cliente.getSexo());
		    return new ResponseEntity<>(clienteRepo.save(objeto), HttpStatus.OK);
		} else {
			throw new ModeloNotFoundException("Cliente no encontrado con Rut: " + cliente.getRut());
		}
	}
	
	@DeleteMapping("/{rut}")
	public ResponseEntity<HttpStatus> eliminar(@PathVariable("rut") int rut) {
		Optional<Cliente> objeto = clienteRepo.findByRut(rut);
		if (objeto.isPresent()) {
			clienteRepo.deleteByRut(rut);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			throw new ModeloNotFoundException("Cliente no encontrado con Rut: " + rut);
		}
	}
	
	@DeleteMapping
	public ResponseEntity<HttpStatus> eliminarTodos() {
			clienteRepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
