package be.vinci.ipl.amazing.wishlists.data;

import be.vinci.ipl.amazing.wishlists.model.Product;
import be.vinci.ipl.amazing.wishlists.model.Wish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
public interface WishRepository extends CrudRepository<Wish, Integer> {
    boolean existsByPseudoAndProductId(String pseudo, int productId);
    Optional<Wish> findByPseudoAndProductId(String pseudo, int productId);

    @Transactional
    void deleteByPseudoAndProductId(String pseudo, int productId);

    Iterable<Wish> findByPseudo(String pseudo);



}
