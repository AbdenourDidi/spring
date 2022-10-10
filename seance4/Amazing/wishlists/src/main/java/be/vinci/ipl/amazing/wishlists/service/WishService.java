package be.vinci.ipl.amazing.wishlists.service;

import be.vinci.ipl.amazing.wishlists.data.ProductProxy;
import be.vinci.ipl.amazing.wishlists.data.WishRepository;
import be.vinci.ipl.amazing.wishlists.exceptions.WishExists409Exception;
import be.vinci.ipl.amazing.wishlists.exceptions.WishNotFound404Exception;
import be.vinci.ipl.amazing.wishlists.model.NoIdWish;
import be.vinci.ipl.amazing.wishlists.model.Product;
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
     * Add product to user wishlist
     * @param pseudo Pseudo of the user
     * @param productId ID of the product
     * @return True if the product was added, false if it was already there
     */
    public boolean addWish(String pseudo, long productId) {
        if (repository.existsByPseudoAndProductId(pseudo, productId)) return false;
        repository.save(new Wish(pseudo, productId));
        return true;
    }

    /**
     * Updtaes a wish in repository
     * @param noIdWish new values of the wish
     * @throws WishNotFound404Exception when the wish couldn't be found
     */
    public void updateOne(NoIdWish noIdWish) throws WishExists409Exception {
        Wish oldWish = repository.findByPseudoAndProductId(noIdWish.getPseudo(), noIdWish.getProductId())
                .orElseThrow(WishNotFound404Exception::new);
        repository.save(noIdWish.toWish(oldWish.getId()));
    }

    /**
     * Deletes a wish from repository
     *
     * @param noIdWish Wish to create
     * @throws WishNotFound404Exception when the wish couldn't be found
     */
    public boolean deleteOne(NoIdWish noIdWish) throws WishNotFound404Exception {
        if (!repository.existsByPseudoAndProductId(noIdWish.getPseudo(), noIdWish.getProductId())) return false;
        repository.deleteByPseudoAndProductId(noIdWish.getPseudo(), noIdWish.getProductId());
        return true;
    }

    /**
     * Reads all wishs from a user
     * @param pseudo Pseudo of the user
     * @return The list of wishs from this user
     */
    public Iterable<Product> readFromUser(String pseudo) {
        Iterable<Wish> wishs = repository.findByPseudo(pseudo);
        return StreamSupport.stream(wishs.spliterator(), false)
                .map(wish ->productProxy.readOne(wish.getProductId()))
                .toList();
    }

}
