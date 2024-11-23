package com.springbatch.springbatch.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import com.springbatch.springbatch.processor.StudentProcessor;
import com.springbatch.springbatch.writer.StudentWriter;
import com.springbatch.springbatch.model.Student;
import com.springbatch.springbatch.model.StudentFieldSetMapper;
import org.springframework.scheduling.annotation.EnableScheduling;






@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {



    // Constantes :
    private static final String FILE_NAME = "/Users/sjezequel/Desktop/Dataset_SpringBatch/results.csv";
    private static final String JOB_NAME = "listStudentsJob";
    private static final String STEP_NAME = "processingStep";
    private static final String READER_NAME = "studentItemReader";



    // Liste des champs headers :
    @Value("${header.names}")
    private String names;



    // Délimiteur à la fin de chaque ligne :
    @Value("${line.delimiter}")
    private String delimiter;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;





    /********************** Méthodes **********************/

    /**
     * Configuration de l'étape.
     * @return
     */
    @Bean
    public Step studentStep() {
        return stepBuilderFactory.get(STEP_NAME)
                .<Student, Student>chunk(5)
                .reader(studentItemReader())
                .processor(studentItemProcessor())
                .writer(studentItemWriter())
                .build();
    }



    /**
     * Affectation des étapes au job.
     * @param step1
     * @return
     */
    @Bean
    public Job listStudentsJob(Step step1) {
        return jobBuilderFactory.get(JOB_NAME)
                .start(step1)
                .build();
    }



    /**
     * Lecteur du fichier csv.
     * @return
     */
    @Bean
    public ItemReader<Student> studentItemReader() {
        FlatFileItemReader<Student> reader = new FlatFileItemReader<>();
        reader.setResource(new PathResource(FILE_NAME));
        reader.setName(READER_NAME);
        reader.setLinesToSkip(1);    // On saute la première ligne.
        reader.setLineMapper(lineMapper());
        return reader;

    }



    /**
     * Configuration du LineMapper pour mapper chaque ligne du fichier CSV en un objet Student.
     * @return LineMapper<Student> configuré pour mapper les lignes du fichier CSV.
     */
    @Bean
    public LineMapper<Student> lineMapper() {
        // Crée une instance de DefaultLineMapper pour mapper les lignes du fichier CSV :
        final DefaultLineMapper<Student> defaultLineMapper = new DefaultLineMapper<>();
        // Crée une instance de DelimitedLineTokenizer pour tokeniser les lignes du fichier CSV :
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        // Définit le délimiteur utilisé pour séparer les champs dans chaque ligne du fichier CSV :
        lineTokenizer.setDelimiter(delimiter);
        // Indique que le tokenizer ne doit pas être strict, ce qui signifie qu'il peut tolérer des lignes avec un nombre différent de champs :
        lineTokenizer.setStrict(false);
        // Définit les noms des champs en utilisant les noms spécifiés dans la variable 'names', séparés par le délimiteur :
        lineTokenizer.setNames(names.split(delimiter));
        // Crée une instance de StudentFieldSetMapper pour mapper les champs tokenisés en un objet Student :
        final StudentFieldSetMapper fieldSetMapper = new StudentFieldSetMapper();
        // Associe le DelimitedLineTokenizer au DefaultLineMapper :
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        // Associe le StudentFieldSetMapper au DefaultLineMapper :
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        // Retourne le DefaultLineMapper configuré :
        return defaultLineMapper;
    }



    /**
     * Processeur (Map/Transforme les lignes des fichiers en objets et inversements).
     * 
     * @return
     */
    @Bean
    public ItemProcessor<Student, Student> studentItemProcessor() {
        return new StudentProcessor();
    }



    /**
     * Writer (écrit les données dans un autre fichier).
     * 
     * @return
     */
    @Bean
    public ItemWriter<Student> studentItemWriter() {
        return new StudentWriter();
    }




}


