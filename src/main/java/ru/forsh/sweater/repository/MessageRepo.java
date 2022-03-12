package ru.forsh.sweater.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.forsh.sweater.domain.Message;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {

    @Query("select m from Message m where m.tag = ?1")
    List<Message> findByTag(String tag);
}
