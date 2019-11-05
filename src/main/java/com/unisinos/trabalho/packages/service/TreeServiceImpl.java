package com.unisinos.trabalho.packages.service;

import com.unisinos.trabalho.packages.domain.*;
import com.unisinos.trabalho.packages.generic.BinaryTree;
import com.unisinos.trabalho.packages.generic.INodeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Validator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {

	private BinaryTree cpfTree = new BinaryTree();

	private BinaryTree nameTree = new BinaryTree();

	private BinaryTree birthdateTree = new BinaryTree();

	private List<Person> people = new ArrayList<>();

	private int elements = 0;

	@Override
	public BinaryTree getCpfTree() {
		return cpfTree;
	}

	@Override
	public BinaryTree getNameTree() {
		return nameTree;
	}

	@Override
	public BinaryTree getBirthdateTree() {
		return birthdateTree;
	}

	@Override
	public List<Person> getPeople() {
		return people;
	}

	@Override
	public List<INodeValue> filterByBirthdatePeriod(LocalDate startDate, LocalDate endDate) {
		if (startDate == null || endDate == null) throw new RuntimeException("Uma das datas está nula");
		else if (startDate.isAfter(endDate)) throw new RuntimeException("Data inicial menor que a data final");

		return birthdateTree.searchListBy(new BirthdateSearchTerm(startDate, endDate));
	}

	@Override
	public void insertPeople(List<Person> people) {
		people.forEach(person -> {
			validatePerson(person);

			final boolean alreadyInserted = cpfTree.searchBy(new CpfSearchTerm(person.getCpf())) != null;

			if (!alreadyInserted) {
				this.people.add(person);
				cpfTree.insert(new StringKey(person.getCpf(), elements));
				nameTree.insert(new StringKey(person.getName(), elements));
				birthdateTree.insert(new LocalDateKey(person.getDataNascimento(), elements));

				elements++;
			}
		});
	}

	private void validatePerson(Person person) {
		if (person.getCpf() == null) throw new RuntimeException("CPF não pode ser null");
		if (person.getDataNascimento() == null) throw new RuntimeException("Data de Nascimento não pode ser null");
		if (person.getName() == null) throw new RuntimeException("Nome não pode ser null");
		if (person.getCidadeNascimento() == null) throw new RuntimeException("Cidade de Nascimento não pode ser null");
		if (person.getRg() == null) throw new RuntimeException("RG não pode ser null");
	}

	@Override
	public List<INodeValue> filterByName(String name) {
		if (name == null) throw new RuntimeException("Nome não pode ser null");
		return nameTree.searchListBy(new NameSearchTerm(name));
	}

	@Override
	public INodeValue filterByCpf(String cpf) {
		if (cpf == null) throw new RuntimeException("Cpf não pode ser null");
		return cpfTree.searchBy(new CpfSearchTerm(cpf));
	}

	@Override
	public void clear() {
		cpfTree = new BinaryTree();
		nameTree = new BinaryTree();
		birthdateTree = new BinaryTree();
		people = new ArrayList<>();
		elements = 0;
	}

}
