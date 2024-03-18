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


    @Test
    public void saveStudent_studentId_0_saveSuccess() {
        assertEquals(service.saveStudent("10", "aa", 222), 0);
    }

    @Test
    public void saveStudent_studentId_1_saveFail() {
        assertEquals(service.saveStudent("1", "aa", 222), 1);
    }

    @Test
    public void saveStudent_studentId_negative1_saveFail() {
        assertEquals(service.saveStudent("-1", "aa", 222), 1);
    }
}
