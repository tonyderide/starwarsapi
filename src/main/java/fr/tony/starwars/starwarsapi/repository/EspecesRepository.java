package fr.tony.starwars.starwarsapi.repository;


import fr.tony.starwars.starwarsapi.models.Especes;
import org.springframework.data.repository.CrudRepository;

public interface EspecesRepository extends CrudRepository<Especes, Integer> { // TODO a adapter en fonction de l'id dans espece
}
