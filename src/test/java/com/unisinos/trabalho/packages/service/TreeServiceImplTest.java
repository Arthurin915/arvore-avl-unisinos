package com.unisinos.trabalho.packages.service;

import com.unisinos.trabalho.packages.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TreeServiceImplTest {

    @InjectMocks
    private TreeServiceImpl service;

    @Test
    public void insertPeople__OnePerson() {
        // Arrange
        final List<Person> personList = Arrays.asList(getRandomPerson("1"));

        // Act
        service.insertPeople(personList);

        // Assert
        Assert.isTrue(service.getPeople().size() == 1);
    }

    @Test
    public void insertPeople__MultiplePeople() {
        // Arrange
        final List<Person> personList = Arrays.asList(getRandomPerson("2"), getRandomPerson("1"), getRandomPerson("3"), getRandomPerson("4"), getRandomPerson("5"), getRandomPerson("6"));

        // Act
        service.insertPeople(personList);

        // Assert
        Assert.isTrue(service.getPeople().size() == 6);
    }

    @Test
    public void insertPeople__DuplicatePeople() {
        // Arrange
        final List<Person> personList = Arrays.asList(getRandomPerson("1"), getRandomPerson("2"), getRandomPerson("3"), getRandomPerson("4"), getRandomPerson("5"), getRandomPerson("6"), getRandomPerson("7"), getRandomPerson("8"), getRandomPerson("4"));

        // Act
        service.insertPeople(personList);

        // Assert
        Assert.isTrue(service.getPeople().size() == 8);
    }

    private Person getRandomPerson(String cpf) {
        return new Person(cpf, "", "", LocalDate.now(), "");
    }
}