package ssvv.example;

import domain.Student;
import org.junit.Before;
import org.junit.Test;
import repository.StudentRepository;
import validation.StudentValidator;

import java.util.Iterator;

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
    @Test
    public void testNumeNull() {
        repository.save(new Student("112", null, 113));
        assert repository.findOne("112") == null;
    }

    @Test
    public void testIdValidBVA() {
        repository.save(new Student("112", "dav", 113));
        assert repository.findOne("112") != null;
        repository.save(new Student("1", "david", 113));
        assert repository.findOne("1") != null;
        repository.save(new Student("abc", "ama", 113));
        assert repository.findOne("abc") != null;
    }

    @Test
    public void testIdInvalidBVA() {
        repository.save(new Student("", "dav", 113));
        assert repository.findOne("") == null;
        repository.save(new Student(null, "david", 113));
        Iterator<Student> iterator = repository.findAll().iterator();
        assert !iterator.hasNext();
    }

    @Test
    public void testNameValidBVA() {
        repository.save(new Student("112", "dav", 113));
        assert repository.findOne("112") != null;
        repository.save(new Student("113", "a", 113));
        assert repository.findOne("113") != null;
        repository.save(new Student("114", "i0n", 113));
        assert repository.findOne("114") != null;
    }

    @Test
    public void testNameInvalidBVA() {
        repository.save(new Student("113", null, 113));
        assert repository.findOne("113") == null;
        repository.save(new Student("114", "", 113));
        assert repository.findOne("114") == null;
    }

    @Test
    public void testGroupLowerBound() {
        repository.save(new Student("113", "dav", 110));
        assert repository.findOne("113") == null;
        repository.save(new Student("114", "david", 111));
        assert repository.findOne("114") != null;
        repository.save(new Student("115", "ama", 112));
        assert repository.findOne("115") != null;
    }

    @Test
    public void testGroupUpperBound() {
        repository.save(new Student("113", "dav", 936));
        assert repository.findOne("113") != null;
        repository.save(new Student("114", "david", 937));
        assert repository.findOne("114") != null;
        repository.save(new Student("115", "ama", 938));
        assert repository.findOne("115") == null;
    }


}
