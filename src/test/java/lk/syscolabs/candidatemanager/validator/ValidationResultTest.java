package lk.syscolabs.candidatemanager.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationResultTest {
    private ValidationResult validationResult;

    @Before
    public void init() {
        validationResult = new ValidationResult();
    }

    @Test
    public void testOne() {
        assertEquals(false, validationResult.hasError());
        validationResult.addError("f1", "e1");
        assertEquals(true, validationResult.hasError());
        assertEquals(false, validationResult.getErrors().isEmpty());
    }

    @Test
    public void testTwo() {
        assertEquals(false, validationResult.hasError());
        assertEquals(true, validationResult.getErrors().isEmpty());
    }
}
