package com.springbatch.springbatch.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;






/**
 * Planificateur. 
 * Classe qui planifie l'exécution du job.
 * 
 */
@Component
@Slf4j
public class Scheduler {



    // Injection du batch Launcher :
    @Autowired
    private BatchLauncher batchLauncher;

    

    // Le batch est lancé toutes les 8s (8000mls) :
    @Scheduled(fixedDelay = 8000)
    public void perform() throws Exception {
        log.info("Batch programme; pour tourner toutes les 8 secondes");
        batchLauncher.run();
    }



}

