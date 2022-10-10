package be.vinci.ipl.amazing.wishlists.data;

import be.vinci.ipl.amazing.wishlists.model.Product;
import be.vinci.ipl.amazing.wishlists.model.Wish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
public interface WishRepository extends CrudRepository<Wish, Long> {
    boolean existsByPseudoAndProductId(String pseudo, long productId);
    Optional<Wish> findByPseudoAndProductId(String pseudo, long productId);

    @Transactional
    void deleteByPseudoAndProductId(String pseudo, long productId);

    Iterable<Wish> findByPseudo(String pseudo);



}
