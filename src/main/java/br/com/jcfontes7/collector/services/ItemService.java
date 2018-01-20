package br.com.jcfontes7.collector.services;

import br.com.jcfontes7.collector.models.Item;
import br.com.jcfontes7.collector.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findNameContaining(String name){
        return itemRepository.findByNameContaining(name);
    }

    public Item findById(String id) {
        return itemRepository.findOne(id);
    }

    public Item findByName(String name) {
        Item itemR =  itemRepository.findByName(name);

        if(itemR != null && !itemR.getId().equalsIgnoreCase("")){
            return itemR;
        }else {
            return new Item();
        }
    }

    public void save(Item item){
        Item itemR =  itemRepository.findByName(item.getName());

        if(itemR != null && !itemR.getId().equalsIgnoreCase("")){
            item.setId(itemR.getId());
        }

        itemRepository.save(item);
    }

    public void delete(String id){
        Item item = itemRepository.findOne(id);

        if(item != null) {
            itemRepository.delete(item);
        }
    }

    public void update(Item item) {
        Item saved = itemRepository.findOne(item.getId());

        if(saved != null) {
            saved.update(item);
            itemRepository.save(saved);
        }
    }
}
