package com.karapinar.creditmanagement;

import com.karapinar.creditmanagement.model.Applicant;
import com.karapinar.creditmanagement.repository.ApplicantRepository;
import com.karapinar.creditmanagement.repository.CreditApplicationRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AutomaticAssesementTests {

    @Mock
    private CreditApplicationRepository creditApplicationRepository;

    @Mock
    private ApplicantRepository applicantRepository;

    @InjectMocks
    private CreditApplicationService creditApplicationService;

    @InjectMocks
    private ApplicantService applicantService;

    @InjectMocks
    private CreditAssesementService creditAssesementService;



    @ParameterizedTest
    @CsvSource({
            "Dummy 1, 700, 1",
            "-1, null"
    })
    void shouldHandleInvalidUser(String name, Long creditAmount, Double CreditScore, boolean isBankCustoemr) {
        // Arrange
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(userId);
        });


    }


}
