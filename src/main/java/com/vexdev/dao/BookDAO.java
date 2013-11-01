package com.vexdev.dao;

import com.sun.istack.internal.NotNull;
import com.vexdev.constant.SystemConstants;
import com.vexdev.dao.interfaces.BaseDAO;
import com.vexdev.models.Author;
import com.vexdev.models.Book;
import com.vexdev.models.Category;
import com.vexdev.models.Editor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oslinux
 * Date: 19/10/13
 * Time: 00:53
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class BookDAO extends BaseDAO<Book> {
    public static final boolean D = SystemConstants.ACTIVE_DEBUG;
    private static final Logger log = Logger.getLogger(BookDAO.class);

    private static final Class CLASS = Book.class;

    /**
     * Updates / Sets a field in an existing Book.
     * @param book Book to update
     * @param field Field to set
     * @param value Value to set
     * @return Boolean true if no errors.
     */
    @Transactional
    public boolean setField(@NotNull Book book, @NotNull String field, @NotNull String value) {
        try {
            if(field.equalsIgnoreCase("isbn")) {
                book.setIsbn(value);
            } else if(field.equalsIgnoreCase("name")) {
                book.setName(value);
            } else if(field.equalsIgnoreCase("url")) {
                book.setUrl(value);
            } else if(field.equalsIgnoreCase("imgurl")) {
                book.setImgurl(value);
            } else if(field.equalsIgnoreCase("desc")) {
                book.setDescription(value);
            } else if(field.equalsIgnoreCase("price")) {
                value = value.trim();
                Long priceValue = Long.valueOf(value.replaceAll("\\D+",""));
                Integer priceScale = value.length() - 1 - (value.indexOf(',')==-1?value.indexOf('.'):value.indexOf(','));
                book.setPrice(BigDecimal.valueOf(priceValue, priceScale));
            } else if(field.equalsIgnoreCase("pages")) {
                value = value.trim();
                Integer pages = Integer.valueOf(value.replaceAll("\\D+",""));
                book.setPages(pages);
            } else if(field.equalsIgnoreCase("edition")) {
                value = value.trim();
                Integer edition = Integer.valueOf(value.replaceAll("\\D+",""));
                book.setEdition(edition);
            } else {
                return false;
            }
            return true;
        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }

    /**
     * Persists a new Book
     * @param book Book to persist
     */
    @Transactional
    public void persist(@NotNull Book book) {
        List<Author> authors = book.getAuthors();
        book.setAuthors(new ArrayList<Author>());
        List<Category> categories = book.getCategories();
        book.setCategories(new ArrayList<Category>());
        List<Editor> editors = book.getEditors();
        book.setEditors(new ArrayList<Editor>());
        super.persist(book);
        for(Author author : authors)
            book.getAuthors().add(author);
        for(Category category : categories)
            book.getCategories().add(category);
        for(Editor editor : editors)
            book.getEditors().add(editor);
    }

    @Override
    public Class getEntityClass() {
        return CLASS;
    }

    @Override
    public String getIDName() {
        return "isbn";
    }
}
