package io.github.kenneth.framework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.kenneth.application.BookReserveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookReservePublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    /**
     * Method responsible for publish a message to reserve queue
     *
     * @param bookReserveDTO field DTO for reserve a book
     */
    public void reservationPublisher(BookReserveDTO bookReserveDTO) {
        String json;
        json = convertIntoJson(bookReserveDTO);
        rabbitTemplate.convertAndSend(queue.getName(), json);
    }

    /**
     * Method responsible for convert object Java to JSON
     *
     * @param bookReserveDTO field DTO for reserve a book
     */
    private String convertIntoJson(BookReserveDTO bookReserveDTO) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(bookReserveDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
