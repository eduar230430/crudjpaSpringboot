package com.example.crud.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.demo.dao.EmployeeDAO;
import com.example.crud.demo.model.Employee;

/*
 * clase para interactuar entre los elementos de entrada como de salida de la API en las rutas usuario
 * los metodos descritos delega informacion de manera mas ordenada a la aplicacion y a las diferentes capas de desarrollo
 * la capa de desarrollo que se crea dentro de esta aplicacion con jpa solo ecesita la instancia de la interface EmployeeDAO
 * dentro de esta clase se usa OptionalOptional, este es un wrapper que nos ayuda a prevenir los nullpointer tan comunes en Java. 
 * Optional nos da un métodos adecuados para validar si el valor contenido esta null o no.
 * */

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeDAO employeeDAO;

	// metodo que muestra cada registro existente dentro de la base de datos
	@GetMapping("/employee")
	public List<Employee> get() {
		return employeeDAO.findAll();
	}

	// se guarda un registro dentro de la bd
	@PostMapping("/employee")
	public Employee save(@RequestBody Employee employeeObj) {
		employeeDAO.save(employeeObj);
		return employeeObj;
	}

	@GetMapping("/employee/{id}")
	public Employee get(@PathVariable int id) {
		// conicion para determinar si un empleado existe o no en la base de datos y no
		// lanzar una exception de error en tiempo de ejecicion de la aplicacion
		// ptional nos permite saber si existe dentro de nuestra base de datos un
		// registro si ele elemento es coincidente con el id de busqueda retornamos un
		// result
		Optional<Employee> employee = employeeDAO.findById(id);
		if (employee.isPresent()) {
			return employee.get();
		} else
			throw new RuntimeException("Employee with id:" + id + " is not found");
	}

	@DeleteMapping("/employee/{id}")
	public String delete(@PathVariable int id) {
		Optional<Employee> employee = employeeDAO.findById(id);
		if (employee.isPresent()) {
			employeeDAO.delete(employee.get());
			return "Employee has been deleted with id:" + id;
		} else
			throw new RuntimeException("Employee with id:" + id + " is not found");
	}

	@PutMapping("/employee")
	public Employee update(@RequestBody Employee employeeObj) {
		// si el elemento existe en la base de datos podra ser actualizada su
		// informacion con el metodo save delegado en services y DAO
		return employeeDAO.save(employeeObj);
	}
}
