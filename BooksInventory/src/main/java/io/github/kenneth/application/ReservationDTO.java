package io.github.kenneth.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    
    private Long id;
    private Long student;
    private Long bookId;

}
