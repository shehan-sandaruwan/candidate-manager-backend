package lk.syscolabs.candidatemanager.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ValidatorTest {
    private ValidatorExt validatorExt;

    @Before
    public void init() {
        this.validatorExt = new ValidatorExt();
    }

    @Test
    public void testEmail() {
        assertEquals(true, validatorExt.isValidEmail("asd@email.com"));
        assertEquals(true, validatorExt.isValidEmail("avc.asd@email.com"));
        assertEquals(false, validatorExt.isValidEmail("avc asd@email.com"));
        assertEquals(false, validatorExt.isValidEmail("asdemail.com"));
        assertEquals(false, validatorExt.isValidEmail("asdem@ailcom"));
        assertEquals(false, validatorExt.isValidEmail("asdemailcom"));
    }

    @Test
    public void test() {
        assertEquals(true, validatorExt.onlyContainsNumbers("1"));
        assertEquals(true, validatorExt.onlyContainsNumbers("1111111111111111"));
        assertEquals(false, validatorExt.onlyContainsNumbers("a1"));
        assertEquals(false, validatorExt.onlyContainsNumbers("acsdgvregbrefhbev"));
    }

    class ValidatorExt extends Validator {

        @Override
        public ValidationResult validate(Object o) {
            return null;
        }
    }
}
