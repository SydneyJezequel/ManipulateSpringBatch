package com.springbatch.springbatch.writer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import com.springbatch.springbatch.model.Student;
import lombok.extern.slf4j.Slf4j;






/**
 * Classe du Writer.
 * 
 */
@Slf4j
public class StudentWriter implements ItemWriter<Student> {



    // @Autowired
    // private IStudentService studentService;



    // Variables :
    private static final String FILE_PATH = "students.xml";



    @Override
    public void write(List<? extends Student> students) {
        //students.stream().forEach(student -> {
        // log.info("Enregistrement en base de l'objet.");
        log.info("Enregistrement des éléments.");
        List<Student> studentsList = new ArrayList<>();
        int cpt = 1;
        for(Student student : students){
            log.info("Elément n°" + cpt);
            log.info(student.getRank());
            log.info(student.getFirstName());
            log.info(student.getLastName());
            log.info(student.getCenter());
            log.info(student.getPv());
            log.info(student.getOrigin());
            log.info(student.getMention());
            studentsList.add(student);
        };
        // **************** Logs des éléments **************** //

        // Ecriture et enregistrement du fichier xml :
            /*
            try {
                // Crée une instance de StudentsWrapper pour encapsuler la liste des étudiants :
                StudentsWrapper studentsWrapper = new StudentsWrapper();
                // Définit la liste des étudiants dans le wrapper :
                studentsWrapper.setStudents(studentsList);
                // Crée un contexte JAXB pour la classe StudentsWrapper :
                JAXBContext context = JAXBContext.newInstance(StudentsWrapper.class);
                // Crée un marshaller à partir du contexte JAXB :
                Marshaller marshaller = context.createMarshaller();
                // Configure le marshaller pour produire une sortie XML formatée (indentée) :
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                // Sérialise l'objet studentsWrapper en XML et écrit le résultat dans un fichier à l'emplacement spécifié par FILE_PATH :
                marshaller.marshal(studentsWrapper, new File(FILE_PATH));
                // Enregistre un message d'information dans les logs indiquant que le fichier XML a été généré avec succès :
                log.info("Fichier XML généré avec succès : " + FILE_PATH);
            } catch (JAXBException e) {
                // En cas d'exception JAXB, enregistre un message d'erreur dans les logs et relance l'exception.
                log.error("Erreur lors de la génération du fichier XML", e);
                throw e;
            }
            */
            // Service d'insertion de l'élément dans la BDD :
            // studentService.insertStudent(student);
        }




}

