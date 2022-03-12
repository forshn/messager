package ru.forsh.sweater.repository;

import org.springframework.data.repository.CrudRepository;
import ru.forsh.sweater.domain.Message;

public interface MessageRepo extends CrudRepository<Message, Long> {
}
