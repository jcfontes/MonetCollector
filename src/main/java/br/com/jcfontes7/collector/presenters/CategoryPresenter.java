package br.com.jcfontes7.collector.presenters;

import br.com.jcfontes7.collector.models.Category;

public class CategoryPresenter {

    private String id;
    private String name;
    private String description;

    public CategoryPresenter(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
