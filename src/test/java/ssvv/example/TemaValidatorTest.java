package ssvv.example;

import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import validation.TemaValidator;
import validation.ValidationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TemaValidatorTest {

    private TemaValidator validator;

    @Before
    public void setUp() {
        validator = new TemaValidator();
    }

    @Test
    public void testValidTema() {
        Tema tema = new Tema("1", "Descriere valida", 10, 5);
        try {
            validator.validate(tema);
        } catch (ValidationException e) {
            fail("Unexpected ValidationException");
        }
    }

    @Test
    public void testInvalidID() {
        Tema tema = new Tema(null, "Descriere valida", 10, 5);
        try {
            validator.validate(tema);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("ID invalid! \n", e.getMessage());
        }
    }

    @Test
    public void testInvalidDescriere() {
        Tema tema = new Tema("1", null, 10, 5);
        try {
            validator.validate(tema);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("Descriere invalida! \n", e.getMessage());
        }
    }

    @Test
    public void testInvalidDeadline() {
        Tema tema = new Tema("1", "Descriere valida", 15, 5); // Deadline > 14
        try {
            validator.validate(tema);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("Deadline invalid! \n", e.getMessage());
        }
    }

    @Test
    public void testInvalidStartline() {
        Tema tema = new Tema("1", "Descriere valida", 10, 15); // Startline > 14
        try {
            validator.validate(tema);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("Deadline invalid! \n", e.getMessage());
        }
    }

    @Test
    public void testStartlineGreaterThanDeadline() {
        Tema tema = new Tema("1", "Descriere valida", 5, 6); // Startline > Deadline
        try {
            validator.validate(tema);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("Deadline invalid! \n", e.getMessage());
        }
    }

    @Test
    public void testDeadlineBeforeStartline() {
        Tema tema = new Tema("1", "Descriere valida", 4, 5); // Deadline < Startline
        try {
            validator.validate(tema);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("Deadline invalid! \n", e.getMessage());
        }
    }

    @Test
    public void testValidStartlineAndDeadline() {
        Tema tema = new Tema("1", "Descriere valida", 5, 4); // Both within valid range and Startline < Deadline
        try {
            validator.validate(tema);
        } catch (ValidationException e) {
            fail("Unexpected ValidationException");
        }
    }

    @Test
    public void testInvalidStartlineBelowRange() {
        Tema tema = new Tema("1", "Descriere valida", 10, 0); // Startline below valid range
        try {
            validator.validate(tema);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("Data de primire invalida! \n", e.getMessage());
        }
    }
    @Test
    public void testInvalidDeadlineBelowRange() {
        Tema tema = new Tema("1", "Descriere valida", 0, 5); // Deadline below valid range
        try {
            validator.validate(tema);
            fail("Expected ValidationException");
        } catch (ValidationException e) {
            assertEquals("Deadline invalid! \n", e.getMessage());
        }
    }
}