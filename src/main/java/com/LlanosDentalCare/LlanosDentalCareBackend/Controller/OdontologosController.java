package com.LlanosDentalCare.LlanosDentalCareBackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.LlanosDentalCare.LlanosDentalCareBackend.Model.OdontologosModel;
import com.LlanosDentalCare.LlanosDentalCareBackend.Model.PersonasModel;
import com.LlanosDentalCare.LlanosDentalCareBackend.Repository.OdontologosRepository;
import com.LlanosDentalCare.LlanosDentalCareBackend.Repository.PersonasRepository;
import com.LlanosDentalCare.LlanosDentalCareBackend.Repository.UsuariosRepository;

@CrossOrigin(origins = "*")
@RestController
public class OdontologosController {
	@Autowired
	public OdontologosRepository OdontologosRepository;
	@Autowired
	private PersonasRepository personasRepository;

	@Autowired
	private UsuariosRepository usuarioRepository;

	// LISTAR PERSONAS
	@Autowired
	@GetMapping("/api/ListarDientista")
	public List<OdontologosModel> ListarDientista() {
		return OdontologosRepository.findAll();
	}
	
	@PostMapping("/api/addOdontologo")
	public void adicionarOdontologo(@RequestBody OdontologosModel newOdontologo) {
		OdontologosRepository.save(newOdontologo);
	}
}
