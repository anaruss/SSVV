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

import static java.sql.Types.NULL;
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
        assertEquals(service.saveStudent("101", "aki", 222), 0);
    }

    @org.junit.jupiter.api.Test
    public void tc_2_saveStudent_fail_studentId_1() {
        assertEquals(service.saveStudent("101", "aki", 222), 1);
    }

    @org.junit.jupiter.api.Test
    public void tc_3_saveStudent_fail_studentId_10() {
        assertEquals(service.saveStudent("100", "aki", 1), 1);
    }

    @org.junit.jupiter.api.Test
    public void tc_4_saveStudent_fail_studentId_negative1() {
        assertEquals(service.saveStudent("-1", "aa", 222), 1);
    }

    @org.junit.jupiter.api.Test
    public void tc_5_saveStudent_fail_empty_input() {
        assertEquals(service.saveStudent("", "Dan", 222), 1);
    }

    @org.junit.jupiter.api.Test
    public void tc_6_saveStudent_name_emptyString_saveFail() {
        assertEquals(service.saveStudent("20", "", 222), 1);
    }
    @org.junit.jupiter.api.Test
    public void tc_7_saveStudent_success_empty_group_input() {
        assertEquals(service.saveStudent("60", "Dan", NULL), 1);
    }

    @org.junit.jupiter.api.Test
    public void tc_8_saveStudent_fail_studentId_0() {
        assertEquals(service.saveStudent("0", "Dan", 222), 1);
    }

    @org.junit.jupiter.api.Test
    public void tc_9_saveStudent_studentId_maxInt_saveFail() {
        assertEquals(service.saveStudent("maxint", "Alin", 222), 1);
    }

    @org.junit.jupiter.api.Test
    public void tc_10_saveStudent_fail_group_negative() {
        assertEquals(service.saveStudent("20", "Ana", -1), 1);
    }

    @org.junit.jupiter.api.Test
    public void tc_11_saveStudent_group_maxInt_saveFail() {
        assertEquals(service.saveStudent("20", "Ana", Integer.MAX_VALUE), 1);
    }
}
