package com.example.message.sending.app.model;

/**
 * This class is representation of user
 *
 * @author Burak Unlu
 */
public class User {

    private final String name;

    /**
     * Constructor that initializes the User class.
     *
     * @param name String name of the user
     */
    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}