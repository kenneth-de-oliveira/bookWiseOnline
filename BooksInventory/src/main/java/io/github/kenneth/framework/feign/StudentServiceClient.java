package io.github.kenneth.framework.feign;

import feign.FeignException;
import io.github.kenneth.application.ServiceUnavailableException;
import io.github.kenneth.application.StudentRecord;
import io.github.kenneth.framework.StudentResourceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceClient {

    @Autowired
    private StudentResourceClient studentResourceClient;

    /**
     * Method responsible for find student for document
     *
     * @param document field responsible for document information
     */
    public StudentRecord findDocument(String document) throws ServiceUnavailableException {

        try {
            return studentResourceClient.findByDocument(document).getBody();
        } catch (FeignException.FeignClientException ex) {
            throw new ServiceUnavailableException();
        }

    }


}