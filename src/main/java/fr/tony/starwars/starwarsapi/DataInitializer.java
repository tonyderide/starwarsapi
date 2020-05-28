package fr.tony.starwars.starwarsapi;

import fr.tony.starwars.starwarsapi.models.Especes;
import fr.tony.starwars.starwarsapi.repository.EspecesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Cette classe est chargée au lancement de l'application. Elle sert à
 * enregistrer des données en base.
 *
 * @author Josselin Tobelem
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private EspecesRepository especesRepository;


    public DataInitializer(final EspecesRepository especesRepository){

        this.especesRepository = especesRepository;

    }

    public void initData() {

        try {
            Especes human = new Especes();
            Especes droid = new Especes();

// TODO human.setName("human") pour mettre à jour les attributs

            // sauve ttes les entites ssi la table est vide
            if (!especesRepository.findAll().iterator().hasNext()) {
                especesRepository.saveAll(Arrays.asList(human, droid));
            }

        } catch (final Exception ex) {
            logger.error("Exception while inserting mock data {0}", ex);
        }

    }

    private void deleteAll() {
        especesRepository.deleteAll();
    }

    public void logCreated() {
        especesRepository.findAll().forEach(elem -> logger.info(elem.toString())); // TODO il faut générer un toString (a la main) pour utiliser ceci (le toString de lombok peut ne pas marcher..)
    }

    @Override
    public void run(String... args) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        logger.info("******** Initializing Data at {} ***********", LocalDateTime.now().format(formatter));
        deleteAll();
        initData();
//        logCreated();
        logger.info("******** Data initialized at {} ***********", LocalDateTime.now().format(formatter));
    }
}
