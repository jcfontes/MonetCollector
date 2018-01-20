package br.com.jcfontes7.collector.controllers.parameters;

import br.com.jcfontes7.collector.models.Item;

public class ItemParameter {

    private String name;
    private String description;

    public Item convert(String id) {
        Item item = new Item();
        item.setId(id);
        item.setName(this.name);
        item.setDescription(this.description);

        return item;
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
