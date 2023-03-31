package app;

import domain.Student;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import static org.junit.Assert.assertTrue;


/**
 * Unit test for simple App.
 */
public class AppTest
{
    StudentValidator studentValidator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();
    String filenameStudent = "fisiere/Studenti.xml";
    String filenameTema = "fisiere/Teme.xml";
    String filenameNota = "fisiere/Note.xml";
    StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
    TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
    NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
    NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
    Service service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void addStudentTest1() {
        try {
            Student stud = new Student("88","abc", -1, "loranz@loranz");
            assert service.addStudent(stud) == stud;
        }catch (ValidationException e) {
            assert e.toString().equals("validation.ValidationException: Grupa incorecta!");
            e.printStackTrace();
        }
    }

//    @Test
//    public void addStudentTest2() {
//        try {
//            Student stud = new Student("","abc", -1, "loranz@loranz");
//            assert service.addStudent(stud) == stud;
//        }catch (ValidationException e) {
//            assert e.toString().equals("validation.ValidationException: Id incorect! Grupa incorecta!");
//            e.printStackTrace();
//        }
//    }

//    @Test
//    public void addStudentTest3() {
//        try {
//            Student stud = new Student("88","", -1, "loranz@loranz");
//            assert service.addStudent(stud) == stud;
//        }catch (ValidationException e) {
//            assert e.toString().equals("validation.ValidationException: Nume incorect! Grupa incorecta!");
//            e.printStackTrace();
//        }
//    }
}
