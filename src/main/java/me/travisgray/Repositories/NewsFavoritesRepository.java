package me.travisgray.Repositories;

import me.travisgray.Models.NewsFavorites;
import org.springframework.data.repository.CrudRepository;

public interface NewsFavoritesRepository extends CrudRepository<NewsFavorites, Long> {

    Iterable<NewsFavorites> findAllByFavorites(String Favorites);
}
