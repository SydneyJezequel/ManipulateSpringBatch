package com.springbatch.springbatch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;





/**
 * Classe qui représente un étudiant.
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "student")
public class Student {



    // Variables :
    @XmlElement
    String rank;

    @XmlElement
    String firstName;

    @XmlElement
    String lastName;
 
    @XmlElement
    String center;
    
    @XmlElement
    String pv;

    @XmlElement
    String origin;
 
    @XmlElement
    String mention;



    // Constructeur par défaut + Constructeur avec tous les paramètres :
    // (Voir les annotations dans le haut de la classe)



    // Getters & Setters :
    // (Voir les annotations dans le haut de la classe)
  


}

