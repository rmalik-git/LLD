package org.example.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Group {
    private final String id;
    private final String name;
    private List<User> members;
    private Date createdAt;

    public Group(String name, List<User> members ) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.members =members;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return new ArrayList<>(members);
    }
}
