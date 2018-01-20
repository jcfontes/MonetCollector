package br.com.jcfontes7.collector.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Item {

    @Id
    private String id;
    private String name;
    private String description;

    public void update(Item item) {
        this.name = item.getName() != null ? this.name = item.getName() : this.name;
        this.description = item.getDescription() != null ? this.description = item.getDescription() : this.description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
