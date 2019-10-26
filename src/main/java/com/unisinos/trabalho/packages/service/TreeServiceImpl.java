package com.unisinos.trabalho.packages.service;

import com.unisinos.trabalho.packages.generic.BinaryTree;
import org.springframework.stereotype.Service;

@Service
public class TreeServiceImpl implements TreeService {

	BinaryTree tree;

	@Override
	public BinaryTree getTree() {
		return tree;
	}

}
