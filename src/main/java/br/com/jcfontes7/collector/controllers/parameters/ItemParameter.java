package br.com.jcfontes7.collector.controllers.parameters;

import br.com.jcfontes7.collector.models.Category;
import br.com.jcfontes7.collector.models.Item;

public class ItemParameter {

    private String name;
    private String description;
    private String categoryId;

    public Item convert() {
        Item item = new Item();
        item.setName(this.name);
        item.setDescription(this.description);

        Category category = new Category();
        category.setId(this.categoryId);
        item.setCategory(category);

        return item;
    }

    public Item convert(String id) {
        Item item = new Item();
        item.setId(id);
        item.setName(this.name);
        item.setDescription(this.description);

        Category category = new Category();
        category.setId(this.categoryId);
        item.setCategory(category);

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
