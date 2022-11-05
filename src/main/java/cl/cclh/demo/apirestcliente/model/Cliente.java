package cl.cclh.demo.apirestcliente.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Objeto Cliente")
@Document(collection = "Cliente")
public class Cliente {

	@Id 
	private String id;
	
	@ApiModelProperty(notes = "Rut del cliente")
	@NotBlank(message = "Rut es un atributo obligatorio")
    @Indexed(unique = true)
	private int rut;
    
	@ApiModelProperty(notes = "Digito Verificador del Rut")
    @NotBlank(message = "Digito verificador es obligatorio")
    @Size(max = 1, message = "Digito Verificador debe tener maximo 1 caracter")
	private String dv;
	
	@ApiModelProperty(notes = "Nombres del Cliente")
	@NotBlank(message = "Nombres es un atributo obligatorio")
	private String nombres;
	
	@ApiModelProperty(notes = "Apellidos del Cliente")
	@NotBlank(message = "Apellidos es un atributo obligatorio")
	private String apellidos;
	
	@ApiModelProperty(notes = "Domicilio del Cliente")
	private String domicilio;
	
	@ApiModelProperty(value = "Telefono debe tener 9 caracteres")
	private String telefono;
	
	@ApiModelProperty(value = "Email del Cliente")
	@NotBlank(message = "Email es un atributo obligatorio")
	@Email
	@Indexed(unique = true)
	private String email;
	
	@ApiModelProperty(value = "Género")
	private String sexo;
	
	public Cliente(int rut, String dv, String nombres, String apellidos, String domicilio, String telefono,
			String email, String sexo) {
		super();
		this.rut = rut;
		this.dv = dv;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.email = email;
		this.sexo = sexo;
	}

	public Cliente() {
	}

	public String getId() {
		return id;
	}

	public int getRut() {
		return rut;
	}

	public void setRut(int rut) {
		this.rut = rut;
	}

	public String getDv() {
		return dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", rut=" + rut + ", dv=" + dv + ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", domicilio=" + domicilio + ", telefono=" + telefono + ", email=" + email + ", sexo=" + sexo + "]";
	}
	
}
