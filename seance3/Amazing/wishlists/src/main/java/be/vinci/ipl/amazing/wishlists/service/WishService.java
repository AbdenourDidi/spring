package be.vinci.ipl.amazing.wishlists.service;

import be.vinci.ipl.amazing.wishlists.data.ProductProxy;
import be.vinci.ipl.amazing.wishlists.data.WishRepository;
import be.vinci.ipl.amazing.wishlists.exceptions.WishExists409Exception;
import be.vinci.ipl.amazing.wishlists.exceptions.WishNotFound404Exception;
import be.vinci.ipl.amazing.wishlists.model.NoIdWish;
import be.vinci.ipl.amazing.wishlists.model.Wish;

import java.util.stream.StreamSupport;

public class WishService {
    private final WishRepository repository;
    private final ProductProxy productProxy;

    public WishService(WishRepository repository, ProductProxy productProxy) {
        this.repository = repository;
        this.productProxy = productProxy;
    }

    /**
     * Creates a wish in repository
     * @param noIdWish Wish to create
     * @throws WishExists409Exception when wish already exists
     */
    public void createOne(NoIdWish noIdWish) throws WishExists409Exception {
        if (repository.existsByPseudoAndProductId(noIdWish.getPseudo(), noIdWish.getProductId())) {
            throw new WishExists409Exception();
        }
        repository.save(noIdWish.toWish());
    }

    /**
     * Deletes a wish from repository
     *
     * @param noIdWish Wish to create
     * @throws WishNotFound404Exception when the wish couldn't be found
     */
    public void deleteOne(NoIdWish noIdWish) throws WishNotFound404Exception {
        if (!repository.existsByPseudoAndProductId(noIdWish.getPseudo(), noIdWish.getProductId())) throw new WishNotFound404Exception();
        repository.deleteByPseudoAndProductId(noIdWish.getPseudo(), noIdWish.getProductId());
    }

    /**
     * Reads all wishs from a user
     * @param pseudo Pseudo of the user
     * @return The list of wishs from this user
     */
    public Iterable<NoIdWish> readFromUser(String pseudo) {
        Iterable<Wish> wishs = repository.findByPseudo(pseudo);
        return StreamSupport.stream(wishs.spliterator(), false)
                .map(Wish::removeId)
                .toList();
    }





}
