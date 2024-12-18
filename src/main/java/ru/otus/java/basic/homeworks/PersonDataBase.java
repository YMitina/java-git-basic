package ru.otus.java.basic.homeworks;


import java.util.HashMap;
import java.util.Map;

public class PersonDataBase {
    Map<Long, Person> mapPerson;

    public PersonDataBase() {
        mapPerson = new HashMap<Long, Person>();
    }

    public Person findById(Long id) {
        return mapPerson.get(id);
    }

    public void add(Person person) {
        this.mapPerson.put(person.getId(), person);
    }

    public boolean isManager(Person person) {
        return person.getPosition().isManager();
    }

    public boolean isEmployee(Person person) {
        return !person.getPosition().isManager();
    }


}
