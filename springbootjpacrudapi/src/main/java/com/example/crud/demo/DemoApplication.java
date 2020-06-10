package com.example.crud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/* copia del proyecto de RES API ahora con la variante de JPA
 * la plicacion funciona sin el package de sevices (interface service and servicesImp)
 * aplication rest api hibernate with mysql
 * la plicacion usa el modelo DAO en conjunto con un modelo REST para la comunicacion de los datos dentro de MySQL
 * Se ejecutan las pruebas con el programa postman para un uso de los diferente verbos o acciones de la api
 * un crud con una tabla employee.s
 * */

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
