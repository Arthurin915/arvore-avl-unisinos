package com.unisinos.trabalho.packages.controller;


import com.unisinos.trabalho.packages.generic.BinaryTree;
import com.unisinos.trabalho.packages.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main")
public class MainController {

	@Autowired
	private TreeService treeService;

	@GetMapping("/tree")
	public ResponseEntity<BinaryTree> getTree() {
		BinaryTree tree = treeService.getTree();
		return ResponseEntity.ok(tree);
	}

}
