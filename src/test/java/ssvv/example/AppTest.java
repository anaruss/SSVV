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
    public void tc_9_saveStudent_studentId_maxInt_success() {
        assertEquals(service.saveStudent("maxint", "Alin", 222), 0);
    }

    @org.junit.jupiter.api.Test
    public void tc_10_saveStudent_fail_group_negative() {
        assertEquals(service.saveStudent("20", "Ana", -1), 1);
    }

    @org.junit.jupiter.api.Test
    public void tc_11_saveStudent_group_maxInt_saveFail() {
        assertEquals(service.saveStudent("20", "Ana", Integer.MAX_VALUE), 1);
    }


/**
 * WBT - Lab 3
 */

    @org.junit.jupiter.api.Test
    public void wbt_tc_1_saveTema_startlineBeforeDeadline_success() {
        assertEquals(service.saveTema("2", "Gui", 4, 3), 0);
    }

    @org.junit.jupiter.api.Test
    public void wbt_tc_2_saveTema_startlineAfterDeadline_fail() {
        assertEquals(service.saveTema("5", "Coding", 4, 5), 1);
    }

    @org.junit.jupiter.api.Test
    public void wbt_tc_3_saveTema_startline_0_saveFail() {
        assertEquals(service.saveTema("12", "Math", 2, 0), 1);
    }

    @org.junit.jupiter.api.Test
    void wbt_tc_4_saveTema_deadline_13_saveSuccess() {
        assertEquals(service.saveTema("13", "Abc", 13, 12), 0);
    }

    @org.junit.jupiter.api.Test
    void wbt_tc_5_saveTema_deadline_15_saveFail() {
        assertEquals(service.saveTema("14", "Finals", 15, 12), 1);
    }

    @org.junit.jupiter.api.Test
    void wbt_tc_6_saveTema_startline_and_deadline_negative_saveFail() {
        assertEquals(service.saveTema("15", "Exercises", -2, -1), 1);
    }

    @org.junit.jupiter.api.Test
    void wbt_tc_7_saveTema_startline_20_and_deadline_25_saveFail() {
        assertEquals(service.saveTema("16", "A", 25, 20), 1);
    }

    @org.junit.jupiter.api.Test
    void wbt_tc_8_saveTema_id_emptyString_saveFail() {
        assertEquals(service.saveTema("", "Squares", 6,5), 1);
    }

    @org.junit.jupiter.api.Test
    void wbt_tc_9_saveTema_descriere_emptyString_saveFail() {
        assertEquals(service.saveTema("27", "", 6, 5), 1);
    }

/**
 * BBT - Lab 4
 */
    
    @org.junit.jupiter.api.Test
    void bbt_tc_1_saveStudent() {
        assertEquals(service.saveStudent("10", "Louie", 222), 0);
    }

    @org.junit.jupiter.api.Test
    void bbt_tc_2_saveTema() {
        assertEquals(service.saveTema("23", "ana", 13, 12), 0);
    }

    @org.junit.jupiter.api.Test
    void bbt_tc_3_saveNota() {
        assertEquals(service.saveNota("10","23",10,1,"Good enough!"),1);
    }

    @org.junit.jupiter.api.Test
    void bbt_tc_4_saveAll() {
        assertEquals(service.saveStudent("10", "Louie", 222), 0);
        assertEquals(service.saveTema("23", "ana", 13, 12), 0);
        assertEquals(service.saveNota("10", "23", 10, 1, "Good enough"),1);
    }


/**
 * LAB 4 - TH
 */

    @org.junit.jupiter.api.Test
    void incremental_tc_1_saveStudent() {
        // Add a student
        assertEquals(service.saveStudent("10", "aa", 222), 0);
    }

    @org.junit.jupiter.api.Test
    void incremental_tc_2_saveTema() {
        // Add a student
        assertEquals(service.saveStudent("10", "aa", 222), 0);

        // Add an assignment
        assertEquals(service.saveTema("23", "aa", 13, 12), 0);
    }

    @org.junit.jupiter.api.Test
    void incremental_tc_3_saveNota() {
        // Add a student
        assertEquals(service.saveStudent("10", "aa", 222), 0);

        // Add an assignment
        assertEquals(service.saveTema("23", "aa", 13, 12), 0);

        // Add a grade
        assertEquals(service.saveNota("10", "23", 10, 1, "Perfect!"), 1);
    }
    

}