package io.github.kenneth.framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.kenneth.application.dto.BookReserveDTO;
import io.github.kenneth.application.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.naming.ServiceUnavailableException;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookReserveSubscriber {

    private final BookService bookService;

    @RabbitListener(queues = "${mq.queues.book-reserve}")
    public void reserve(@Payload String payload) throws ServiceUnavailableException {
        try {
            var mapper = new ObjectMapper();
            var bookReserveDTO = mapper.readValue(payload, BookReserveDTO.class);
            bookService.reserve(bookReserveDTO.getId());
            log.info("Book reserved successfully");
        } catch (Exception ex) {
            log.error("Failed to reserve book : ", ex.getCause());
            throw new ServiceUnavailableException("Failed to reserve book: Service Unavailable");
        }
    }

}