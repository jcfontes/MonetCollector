package br.com.jcfontes7.collector.repositories;

import br.com.jcfontes7.collector.models.Category;
import br.com.jcfontes7.collector.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

    List<Category> findByNameIsContaining(String name);
}
