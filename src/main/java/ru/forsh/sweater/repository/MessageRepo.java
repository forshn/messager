package ru.forsh.sweater.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.forsh.sweater.domain.Message;

public interface MessageRepo extends CrudRepository<Message, Long> {

    @Query("select m from Message m where m.tag = ?1")
    Page<Message> findByTag(String tag, Pageable pageable);

    Page<Message> findAll(Pageable pageable);


}
