package com.springbatch.springbatch.batch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;






/**
 * Classe de lancement du job.
 * 
 */
@Slf4j
@Component
public class BatchLauncher {



    @Autowired
    private JobLauncher jobLauncher;



    @Autowired
    private Job job;



    /**
     * Méthode qui déclenche l'exécution d'un job Spring Batch.
     * @return Le statut d'exécution du job.
     * @throws JobParametersInvalidException Si les paramètres du job sont invalides.
     * @throws JobExecutionAlreadyRunningException Si le job est déjà en cours d'exécution.
     * @throws JobRestartException Si le job ne peut pas être redémarré.
     * @throws JobInstanceAlreadyCompleteException Si l'instance du job est déjà complète.
     */
    public BatchStatus run() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("Lancement du job.");
        // Crée un ensemble de paramètres pour le job en utilisant le temps actuel comme paramètre unique :    
        JobParameters parameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
        // Lance le job avec les paramètres spécifiés :
        JobExecution jobExecution = jobLauncher.run(job, parameters);
        // Retourne le statut d'exécution du job :
        return jobExecution.getStatus();
    }



}