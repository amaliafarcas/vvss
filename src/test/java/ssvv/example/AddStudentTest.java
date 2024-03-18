package ssvv.example;

import domain.Student;
import org.junit.Before;
import org.junit.Test;
import repository.StudentRepository;
import validation.StudentValidator;

public class AddStudentTest {
    StudentRepository repository;

    @Before
    public void initialize() {
        repository = new StudentRepository(new StudentValidator());
    }

    @Test
    public void testIdEmpty() {
        repository.save(new Student("", "ama", 112));
        assert repository.findOne("") == null;
    }

    @Test
    public void testIdSuccess() {
        repository.save(new Student("932", "ama", 111));
        assert repository.findOne("932") != null;
    }

}
