package io.github.kenneth.framework.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feign.FeignException;
import io.github.kenneth.application.BookRecord;
import io.github.kenneth.application.ServiceUnavailableException;
import io.github.kenneth.framework.BookResourceClient;

@Service
public class BookServiceClient {

    @Autowired
    private BookResourceClient bookResourceClient;

    /**
     * Method responsible for find book by id
     *
     * @param bookId Identification field for the book
     */
    public BookRecord find(Integer bookId) throws ServiceUnavailableException {
        try {
            return bookResourceClient.findById(bookId).getBody();
        } catch (FeignException.FeignClientException ex) {
            throw new ServiceUnavailableException();
        }

    }

}
