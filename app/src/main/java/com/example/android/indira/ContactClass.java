package com.example.android.indira;

public class ContactClass {

    public String id, name, number;

    public ContactClass(String id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public ContactClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
