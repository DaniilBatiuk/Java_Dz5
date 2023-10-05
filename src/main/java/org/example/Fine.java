package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Fine {
    private String type;
    private double amount;

    public Fine(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Тип: " + type + ", Ціна: " + amount;
    }
}

class Person {
    private String code;
    private String name;
    private String city;
    private List<Fine> fines;

    public Person(String code, String name, String city) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.fines = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Fine> getFines() {
        return fines;
    }

    public void addFine(Fine fine) {
        fines.add(fine);
    }

    public void removeFine(int index) {
        if (index >= 0 && index < fines.size()) {
            fines.remove(index);
        }
    }

    public List<Fine> getFinesByType(String fineType) {
        List<Fine> matchingFines = new ArrayList<>();
        for (Fine fine : fines) {
            if (fine.getType().equalsIgnoreCase(fineType)) {
                matchingFines.add(fine);
            }
        }
        return matchingFines;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Код: ").append(code).append(", Ім'я: ").append(name).append(", Місто: ").append(city).append("\n");
        for (Fine fine : fines) {
            sb.append("  ").append(fine).append("\n");
        }
        return sb.toString();
    }
}

class TaxDatabase {
    private Map<String, Person> database;

    public TaxDatabase() {
        database = new HashMap<>();
    }

    public void printDatabase() {
        for (Person person : database.values()) {
            System.out.println(person);
        }
    }

    public void printDataByCode(String code) {
        Person person = database.get(code);
        if (person != null) {
            System.out.println(person);
        } else {
            System.out.println("Людина з кодом " + code + " не знайдена.");
        }
    }

    public void printDataByFineType(String fineType) {
        for (Person person : database.values()) {
            List<Fine> fines = person.getFinesByType(fineType);
            if (!fines.isEmpty()) {
                System.out.println(person);
                for (Fine fine : fines) {
                    System.out.println("Штраф: " + fine);
                }
            }
        }
    }

    public void printDataByCity(String city) {
        for (Person person : database.values()) {
            if (person.getCity().equalsIgnoreCase(city)) {
                System.out.println(person);
            }
        }
    }

    public void addPerson(String code, String name, String city) {
        Person person = new Person(code, name, city);
        database.put(code, person);
    }

    public void addFine(String code, String fineType, double amount) {
        Person person = database.get(code);
        if (person != null) {
            Fine fine = new Fine(fineType, amount);
            person.addFine(fine);
        } else {
            System.out.println("Людина з кодом " + code + " не знайдена.");
        }
    }

    public void removeFine(String code, int fineIndex) {
        Person person = database.get(code);
        if (person != null) {
            person.removeFine(fineIndex);
        } else {
            System.out.println("Людина з кодом " + code + " не знайдена.");
        }
    }

    public void updatePersonInfo(String code, String newName, String newCity) {
        Person person = database.get(code);
        if (person != null) {
            person.setName(newName);
            person.setCity(newCity);
        } else {
            System.out.println("Людина з кодом " + code + " не знайдена.");
        }
    }
}