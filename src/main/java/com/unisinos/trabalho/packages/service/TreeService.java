package com.unisinos.trabalho.packages.service;

import com.unisinos.trabalho.packages.domain.Person;
import com.unisinos.trabalho.packages.generic.BinaryTree;
import com.unisinos.trabalho.packages.generic.INodeValue;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface TreeService {

	BinaryTree getCpfTree();

    BinaryTree getNameTree();

    BinaryTree getBirthdateTree();

    List<Person> getPeople();

    List<INodeValue> filterByBirthdatePeriod(LocalDate startDate, LocalDate endDate);

    void insertPeople(List<Person> people);

    List<INodeValue> filterByName(String name);

    INodeValue filterByCpf(String cpf);

	void clear();
}
