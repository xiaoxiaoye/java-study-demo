package com.remmy;

public class MessageServiceImpl implements MessageService {
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String getMessage() {
        return "Hello World!";
    }
}
