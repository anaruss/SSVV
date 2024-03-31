package ssvv.example;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ssvv.example.domain.Nota;
import ssvv.example.domain.Student;
import ssvv.example.domain.Tema;
import ssvv.example.repository.NotaXMLRepository;
import ssvv.example.repository.StudentXMLRepository;
import ssvv.example.repository.TemaXMLRepository;
import ssvv.example.service.Service;
import ssvv.example.validation.NotaValidator;
import ssvv.example.validation.StudentValidator;
import ssvv.example.validation.TemaValidator;
import ssvv.example.validation.Validator;

import static org.junit.Assert.assertEquals;


/**
 * Unit test for simple App.
 */
public class AppTest
{
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();
    String filenameStudent = "studenti.xml";
    String filenameTema = "teme.xml";
    String filenameNota = "note.xml";

    StudentXMLRepository studentXMLRepository = new StudentXMLRepository((StudentValidator) studentValidator, filenameStudent);
    NotaXMLRepository notaXMLRepository = new NotaXMLRepository((NotaValidator) notaValidator, filenameNota);
    TemaXMLRepository temaXMLRepository = new TemaXMLRepository((TemaValidator) temaValidator, filenameTema);

    Service service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);


    @org.junit.jupiter.api.Test
    public void tc_1_saveStudent_success_studentId_10() {
        assertEquals(service.saveStudent("10", "aa", 222), 0);
    }

    @org.junit.jupiter.api.Test
    public void tc_2_saveStudent_fail_studentId_1() {
        assertEquals(service.saveStudent("1", "aa", 222), 1);
    }

    @org.junit.jupiter.api.Test
    public void tc_3_saveStudent_success_studentId_10() {
        assertEquals(service.saveStudent("10", "aa", 1), 0);
    }

    @org.junit.jupiter.api.Test
    public void tc_4_saveStudent_fail_studentId_negative1() {
        assertEquals(service.saveStudent("-1", "aa", 222), 1);
    }
}
