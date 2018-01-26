package br.com.jcfontes7.collector.controllers.parameters;

import br.com.jcfontes7.collector.models.Category;

public class CategoryParameter {

    private String name;
    private String description;

    public Category convert() {
        Category category = new Category();
        category.setName(this.name);
        category.setDescription(this.description);

        return category;
    }

    public Category convert(String id) {
        Category category = new Category();
        category.setId(id);
        category.setName(this.name);
        category.setDescription(this.description);

        return category;
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
