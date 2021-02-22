package org.sacc.smis.mapper;

import org.sacc.smis.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

//Item表单Mapper
public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findByUpdatedAt(LocalDateTime localDateTime);
}
