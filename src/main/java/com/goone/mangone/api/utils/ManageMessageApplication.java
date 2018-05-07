package com.goone.mangone.api.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ManageMessageApplication {

    @Autowired
    MessageSource messageSource;

    /* Users */
    /* Errors */
    public static final String ERROR_USERS_EMAIL_UNIQUE = "error.user.email.unique";
    public static final String ERROR_USERS_ID_NOT_EXIST = "error.user.user-id.not-exist";
    /* Messages */
    public static final String MESSAGE_USERS_CREATE = "message.user.create";
    public static final String MESSAGE_USERS_READ = "message.user.read";
    public static final String MESSAGE_USERS_UPDATE = "message.user.update";
    public static final String MESSAGE_USERS_DELETE = "message.user.delete";
    public static final String MESSAGE_USERS_SEARCH = "message.user.search";

    /* Categories */
    /* Errors */
    public static final String ERROR_CATEGORIES_NAME_UNIQUE = "error.category.name.unique";
    public static final String ERROR_CATEGORIES_ID_NOT_EXIST = "error.category.category-id.not-exist";
    /* Messages */
    public static final String MESSAGE_CATEGORIES_CREATE = "message.category.create";
    public static final String MESSAGE_CATEGORIES_READ = "message.category.read";
    public static final String MESSAGE_CATEGORIES_UPDATE = "message.category.update";
    public static final String MESSAGE_CATEGORIES_DELETE = "message.category.delete";
    public static final String MESSAGE_CATEGORIES_SEARCH = "message.category.search";

    /* Comics */
    /* Errors */
    public static final String ERROR_COMICS_NAME_UNIQUE = "error.comic.name.unique";
    public static final String ERROR_COMICS_ID_NOT_EXIST = "error.comic.comic-id.not-exist";
    public static final String ERROR_COMICS_IMAGE_SAVE = "error.comic.image.save";
    /* Messages */
    public static final String MESSAGE_COMICS_CREATE = "message.comic.create";
    public static final String MESSAGE_COMICS_READ = "message.comic.read";
    public static final String MESSAGE_COMICS_UPDATE = "message.comic.update";
    public static final String MESSAGE_COMICS_DELETE = "message.comic.delete";
    public static final String MESSAGE_COMICS_SEARCH = "message.comic.search";

    /* Chapters */
    /* Errors */
    public static final String ERROR_CHAPTERS_ID_NOT_EXIST = "error.chapter.chapter-id.not-exist";
    public static final String ERROR_CHAPTERS_IMAGE_SAVE = "error.chapter.image.save";
    /* Messages */
    public static final String MESSAGE_CHAPTERS_CREATE = "message.chapter.create";
    public static final String MESSAGE_CHAPTERS_READ = "message.chapter.read";
    public static final String MESSAGE_CHAPTERS_UPDATE = "message.chapter.update";
    public static final String MESSAGE_CHAPTERS_DELETE = "message.chapter.delete";
    public static final String MESSAGE_CHAPTERS_SEARCH = "message.chapter.search";

    /* Pages */
    /* Errors */
    public static final String ERROR_PAGES_ID_NOT_EXIST = "error.page.page-id.not-exist";
    public static final String ERROR_PAGES_IMAGE_SAVE = "error.page.image.save";
    /* Messages */
    public static final String MESSAGE_PAGES_CREATE = "message.page.create";
    public static final String MESSAGE_PAGES_READ = "message.page.read";
    public static final String MESSAGE_PAGES_UPDATE = "message.page.update";
    public static final String MESSAGE_PAGES_DELETE = "message.page.delete";
    public static final String MESSAGE_PAGES_SEARCH = "message.page.search";

    /* Catalogs */
    /* Errors */
    public static final String ERROR_CATALOGS_CODE_NOT_EXIST = "error.catalog.code.not-exist";

    /* Authentication */
    /* Messages */
    public static final String MESSAGE_AUTHENTICATION_SIGNIN = "message.authentication.signin";
    /* Errors */
    public static final String ERROR_AUTHENTICATION_SIGNIN = "error.authentication.signin";

    /* Unauthorized */
    public static final String ERROR_UNAUTHORIZED = "error.unauthorized";

    public String getMessage(String code){
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

}
