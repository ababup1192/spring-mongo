package org.ababup1192;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class Person {
    @Id
    public String id;
    private String name;
    private List<Item> loves;

    public Person(){}

    public Person(String name, List<Item> loves) {
        this.name = name;
        this.loves = loves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getLoves() {
        return loves;
    }

    public void setLoves(List<Item> loves) {
        this.loves = loves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        return loves != null ? loves.equals(person.loves) : person.loves == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (loves != null ? loves.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", loves=" + loves +
                '}';
    }
}
