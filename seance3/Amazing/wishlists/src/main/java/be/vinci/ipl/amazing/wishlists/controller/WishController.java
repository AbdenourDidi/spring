package be.vinci.ipl.amazing.wishlists.controller;

import be.vinci.ipl.amazing.wishlists.service.WishService;

public class WishController {
    private final WishService service;


    public WishController(WishService service) {
        this.service = service;
    }


}
