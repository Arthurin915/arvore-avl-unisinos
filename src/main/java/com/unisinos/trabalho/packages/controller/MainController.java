package com.unisinos.trabalho.packages.controller;


import com.unisinos.trabalho.packages.domain.Person;
import com.unisinos.trabalho.packages.generic.BinaryTree;
import com.unisinos.trabalho.packages.generic.INodeValue;
import com.unisinos.trabalho.packages.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/main")
public class MainController {

	@Autowired
	private TreeService treeService;

	@GetMapping("/tree/cpf")
	public ResponseEntity<BinaryTree> getCpfTree() {
		BinaryTree tree = treeService.getCpfTree();
		return ResponseEntity.ok(tree);
	}

	@GetMapping("/tree/name")
	public ResponseEntity<BinaryTree> getNameTree() {
		BinaryTree tree = treeService.getNameTree();
		return ResponseEntity.ok(tree);
	}

	@GetMapping("/tree/birthdate")
	public ResponseEntity<BinaryTree> getBirthdateTree() {
		BinaryTree tree = treeService.getBirthdateTree();
		return ResponseEntity.ok(tree);
	}

	@GetMapping("/tree/filter-birthdate")
	public ResponseEntity<List<INodeValue>> filterBirthdateTree(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
		final List<INodeValue> nodes = treeService.filterByBirthdatePeriod(startDate, endDate);
		return ResponseEntity.ok(nodes);
	}

	@GetMapping("/tree/filter-name")
	public ResponseEntity<List<INodeValue>> filterNameTree(@RequestParam("name") String name) {
		final List<INodeValue> nodes = treeService.filterByName(name);
		return ResponseEntity.ok(nodes);
	}

	@GetMapping("/tree/filter-cpf")
	public ResponseEntity<INodeValue> filterCpfTree(@RequestParam("cpf") String cpf) {
		final INodeValue node = treeService.filterByCpf(cpf);
		return ResponseEntity.ok(node);
	}

	@GetMapping("/people")
	public ResponseEntity<List<Person>> getPeople() {
		final List<Person> people = treeService.getPeople();
		return ResponseEntity.ok(people);
	}

	@PostMapping("/people")
	public ResponseEntity postPeople(@RequestBody List<Person> people) {
		treeService.insertPeople(people);
		return ResponseEntity.ok().build();
	}

}
