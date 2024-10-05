package com.demo.blogging.repository;

import com.demo.blogging.entity.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {
    boolean existsByTagName(String tagName);

    Tag findByTagName(String tagName);
}
