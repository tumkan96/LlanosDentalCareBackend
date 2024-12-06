package com.LlanosDentalCare.LlanosDentalCareBackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LlanosDentalCare.LlanosDentalCareBackend.Model.RolesModel;
import com.LlanosDentalCare.LlanosDentalCareBackend.Repository.RolesRepository;

@CrossOrigin(origins = "*")
@RestController
public class RolesController {
	@Autowired
	private RolesRepository rolesRepository;

	@GetMapping("/api/listaRoles")
	public List<RolesModel> ListarRoles() {
		return rolesRepository.findAll();

	}
}