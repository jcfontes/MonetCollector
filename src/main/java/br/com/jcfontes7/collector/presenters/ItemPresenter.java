package br.com.jcfontes7.collector.presenters;

import br.com.jcfontes7.collector.models.Item;

public class ItemPresenter {

    private String id;
    private String name;
    private String description;
    private CategoryPresenter category;

    public ItemPresenter(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.category = new CategoryPresenter(item.getCategory());
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

    public CategoryPresenter getCategory() {
        return category;
    }
}
