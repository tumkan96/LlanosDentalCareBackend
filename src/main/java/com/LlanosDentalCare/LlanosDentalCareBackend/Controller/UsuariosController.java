package com.LlanosDentalCare.LlanosDentalCareBackend.Controller;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.LlanosDentalCare.LlanosDentalCareBackend.Model.PersonasModel;
import com.LlanosDentalCare.LlanosDentalCareBackend.Model.UsuariosModel;
import com.LlanosDentalCare.LlanosDentalCareBackend.Model.Usuarios_RolesModel;
import com.LlanosDentalCare.LlanosDentalCareBackend.Repository.PersonasRepository;
import com.LlanosDentalCare.LlanosDentalCareBackend.Repository.UsuariosRepository;

@CrossOrigin(origins = "*")

@RestController

public class UsuariosController {
	@Autowired
	private UsuariosRepository UsuariosRepository;
	@Autowired
	private PersonasRepository personasRepository;

	@GetMapping("/api/listarUsuarios")
	public List<UsuariosModel> listaUsuarios() {
		return UsuariosRepository.findAll();
	}

	// LOGIN
	@PostMapping("/api/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody UsuariosModel usuario) {
		Map<String, Object> response = new HashMap<>();

		try {
			// Buscar el usuario en la base de datos
			UsuariosModel user = UsuariosRepository.findByUsuarios(usuario.getUsuarios());

			// Validar si el usuario existe y si la contraseña coincide
			if (user != null && user.getContrasena().equals(usuario.getContrasena())) {
				// Obtener roles del usuario desde la lista usuariosRoles
				List<Map<String, Object>> roles = new ArrayList<>();
				for (Usuarios_RolesModel usuarioRol : user.getUsuariosRoles()) {
					// Crear un mapa para cada rol con su id y nombre
					Map<String, Object> roleData = new HashMap<>();
					roleData.put("id_rol", usuarioRol.getRol().getId_rol()); // Asumiendo que RolesModel tiene un
																				// getIdRol()
					roleData.put("nombre", usuarioRol.getRol().getNombre()); // Asumiendo que RolesModel tiene un
																				// getNombre()
					roles.add(roleData);
				}

				// Construir la respuesta
				response.put("authenticated", true);
				response.put("message", "Inicio de sesión exitoso");
				response.put("persona", user.getPersona()); // Información adicional del usuario
				response.put("roles", roles); // Agregar los roles estructurados a la respuesta

				return ResponseEntity.ok(response);
			} else {
				// Respuesta en caso de que la autenticación falle
				response.put("authenticated", false);
				response.put("message", "Usuario o contraseña incorrectos.");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
			}
		} catch (Exception e) {
			// Manejo de excepción
			response.put("authenticated", false);
			response.put("error", "Ocurrió un error durante la autenticación: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@GetMapping("/api/obtenerUltimoUsuario")
	public ResponseEntity<Map<String, String>> obtenerUltimoUsuario() {
	    Map<String, String> response = new HashMap<>();
	    try {
	        String ultimoUsuario = UsuariosRepository.findTopByOrderByUsuariosDesc()
	                                    .map(UsuariosModel::getUsuarios)
	                                    .orElse("u0000");
	        response.put("ultimoUsuario", ultimoUsuario);
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        response.put("error", "Error al obtener el último usuario");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}


	
	
	// Método para asignar usuario y contraseña a la persona
	@PostMapping("/api/asginarUserPassword/{seq_persona}")
	public ResponseEntity<Integer> asignarUserPassword(@PathVariable long seq_persona,
	        @RequestBody Map<String, String> requestBody) {
	    String usuario = requestBody.get("usuario");
	    String contrasena = requestBody.get("contrasena");

	    // Obtener la persona a la que se asignará el usuario
	    Optional<PersonasModel> personaOptional = personasRepository.findById(seq_persona);

	    if (personaOptional.isPresent()) {
	        PersonasModel persona = personaOptional.get();
	        UsuariosModel usuarioExistente = UsuariosRepository.findByUsuarios(usuario);

	        if (usuarioExistente != null) {
	            return new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST); // Usuario ya existe
	        }

	        UsuariosModel nuevoUsuario = new UsuariosModel();
	        nuevoUsuario.setUsuarios(usuario);
	        nuevoUsuario.setContrasena(contrasena);
	        nuevoUsuario.setCod_estado(1);
	        nuevoUsuario.setPersona(persona);

	        UsuariosRepository.save(nuevoUsuario);

	        return new ResponseEntity<Integer>(1, HttpStatus.OK); // Usuario creado exitosamente
	    } else {
	        return ResponseEntity.notFound().build(); // Persona no encontrada
	    }
	}



	// Método para generar el siguiente nombre de usuario
	private String generarNuevoUsuario(String ultimoUsuario) {
		int numero = Integer.parseInt(ultimoUsuario.substring(1)) + 1;
		return String.format("u%04d", numero);
	}

	// Método para generar una contraseña aleatoria
	private String generarContrasenaAleatoria() {
		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String simbolos = "/*-+";
		String numeros = "0123456789";
		SecureRandom random = new SecureRandom();

		// Generar letras aleatorias
		StringBuilder contrasena = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			contrasena.append(letras.charAt(random.nextInt(letras.length())));
		}

		// Agregar un símbolo aleatorio
		contrasena.append(simbolos.charAt(random.nextInt(simbolos.length())));

		// Agregar tres dígitos numéricos aleatorios
		for (int i = 0; i < 3; i++) {
			contrasena.append(numeros.charAt(random.nextInt(numeros.length())));
		}

		return contrasena.toString();
	}

}
