package Switter.Repose;

import Switter.Domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepose extends CrudRepository <Message, Long> {

    List<Message> findByTag(String tag);
}
