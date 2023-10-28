package io.github.kenneth.application;

import io.github.kenneth.domain.Reservation;
import io.github.kenneth.framework.BookReservePublisher;
import io.github.kenneth.framework.ReservationRepository;
import io.github.kenneth.framework.feign.BookServiceClient;
import io.github.kenneth.framework.feign.StudentServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReservationService {

    @Autowired
    private BookServiceClient bookServiceClient;

    @Autowired
    private BookReservePublisher bookReservePublisher;

    @Autowired
    private StudentServiceClient studentServiceClient;

    @Autowired
    private ReservationRepository repository;

    /**
     * Method responsible for reserve book
     *
     * @param document field responsible for document information
     * @param bookId Identification field for the book
     */
    public void reservation(String document, Integer bookId) throws ServiceUnavailableException, BusinessException {

        var student = studentServiceClient.findDocument(document);
        var book = bookServiceClient.find(bookId);
        this.validateReserve(student, book);
        this.createReserve(student, book);
        log.info("Book reserved successfully: {}", book.id());

    }

    /**
     * Method responsible for reserve book
     *
     * @param student field for reserve a student
     * @param book field for reserve a book
     */
    private void createReserve(StudentRecord student, BookRecord book) {
        var reservation = new Reservation();
        reservation.setBookId(book.id());
        reservation.setStudentId(student.id());
        reservation.setBookTitle(book.title() + " - " + book.authorName());
        Reservation reserved = repository.save(reservation);
        this.publishMessageToQueue(reserved);
    }

    /**
     * Method responsible for listing reserved books
     *
     * @param document field relating to student identification
     */
    public List<String> findAll(String document) throws ServiceUnavailableException {
        var student = studentServiceClient.findDocument(document);
        return repository.findByStudentId(student.id()).stream()
                .map(Reservation::getBookTitle)
                .toList();
    }

    /**
     * Method responsible for validate reserve
     */
    private void validateReserve(StudentRecord student, BookRecord book) throws BusinessException {
        this.validateNumberReservations(student);
        this.validateExistsReservation(student, book);
    }

    /**
     * Method responsible for validate number reservations
     */
    private void validateNumberReservations(StudentRecord studentRecord) throws BusinessException {
        var reservations = repository.findByStudentId(studentRecord.id());
        if (reservations.size() >= 10) {
            log.error("Failed to reserve book. Maximum number of reservation : {}", reservations.size());
            throw new BusinessException("Failed to reserve book. Maximum number of reservation.", HttpStatus.BAD_REQUEST.value());
        }
    }

    /**
     * Method responsible for validate if exists reservations
     */
    private void validateExistsReservation(StudentRecord student, BookRecord book) throws BusinessException {
        var reservation = repository.findByStudentIdAndBookId(student.id(), book.id());
        if(reservation != null){
            log.error("Failed to reserve book. Book already reserved : {}", book.id());
            throw new BusinessException("Failed to reserve book. Book already reserved.", HttpStatus.BAD_REQUEST.value());
        }
    }

    /**
     * Method responsible for publish Message To Queue
     */
    private void publishMessageToQueue(Reservation reserved) {
        BookReserveDTO bookReserveDTO = BookReserveDTO.builder().id(reserved.getBookId()).build();
        bookReservePublisher.reservationPublisher(bookReserveDTO);
    }

}