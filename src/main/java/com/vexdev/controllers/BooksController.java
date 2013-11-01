package com.vexdev.controllers;

import com.sun.istack.internal.NotNull;
import com.vexdev.constant.SystemConstants;
import com.vexdev.dao.interfaces.BaseDAO;
import com.vexdev.manager.JSONManager;
import com.vexdev.models.Author;
import com.vexdev.models.Book;
import com.vexdev.models.Category;
import com.vexdev.models.Editor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasRole('admin')")
@RequestMapping(value = "/books")
public class BooksController {

    @Autowired
    BaseDAO<Book> bookDAO;
    @Autowired
    BaseDAO<Author> authorDAO;
    @Autowired
    BaseDAO<Category> categoryDAO;
    @Autowired
    BaseDAO<Editor> editorDAO;

    private Book mockBook;

    public static final boolean D = SystemConstants.ACTIVE_DEBUG;
    private static final Logger log = Logger.getLogger(BooksController.class);

    /**
     * Shows a list of books.
     * @param map Map of attributes to pass to JSP.
     * @return View to load.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String books(ModelMap map) {
        if(D) log.debug("Called: books([map])");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        map.addAttribute("userDetails", userDetails);
        List<Book> books = bookDAO.list();
        map.addAttribute("books", books);
        List<Author> authors = authorDAO.list();
        map.addAttribute("authors", authors);
        List<Editor> editors = editorDAO.list();
        map.addAttribute("editors", editors);
        List<Category> categories = categoryDAO.list();
        map.addAttribute("categories", categories);
        mockBook = new Book();
        return "books";
    }

    /**
     * Adds an image (Or updates existing) to the book.
     * @param isbn Book's ISBN
     * @param request Multipart request with file.
     * @return JSON Result
     */
    @RequestMapping(value = "/addImage/{isbn}", method = RequestMethod.POST)
    public @ResponseBody String addImage(@PathVariable(value="isbn") String isbn, MultipartHttpServletRequest request) {
        if(D) log.debug("Called: addImage(["+isbn+", request])");
        return storeFileOrImage(isbn, request, "imgurl");
    }

    /**
     * Adds a file (Or updates existing) to the book.
     * @param isbn Book's ISBN
     * @param request Multipart request with file.
     * @return JSON Result
     */
    @RequestMapping(value = "/addFile/{isbn}", method = RequestMethod.POST)
    public @ResponseBody String addFile(@PathVariable(value="isbn") String isbn, MultipartHttpServletRequest request) {
        if(D) log.debug("Called: addFile(["+isbn+", request])");
        return storeFileOrImage(isbn, request, "url");
    }

    /**
     * Stores a file or an image, from upload to directory
     * @param isbn Book's ISBN
     * @param request Multipart request with file.
     * @param bookParameter Parameter of book where we should store the URL.
     * @return JSON Result
     */
    private String storeFileOrImage(String isbn, MultipartHttpServletRequest request, String bookParameter) {
        try {
            String baseURL = request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort();

            Book book = bookDAO.getByID(isbn);
            if(book == null) throw new IllegalArgumentException("Book id:"+isbn+" not found.");

            Iterator<String> itr =  request.getFileNames();
            MultipartFile mpf;

            while(itr.hasNext()){
                mpf = request.getFile(itr.next());
                log.info("Received File: " + mpf.getOriginalFilename());

                File file = new File(request.getSession().getServletContext().getRealPath("/resources/uploads/"), mpf.getOriginalFilename());
                file.getParentFile().mkdirs();
                if(file.exists()) {
                    if(D) log.debug("File exists already.");
                    file.delete();
                }
                file.createNewFile();
                mpf.transferTo(file);

                storeParameter(isbn, bookParameter, baseURL+"/resources/uploads/"+mpf.getOriginalFilename());

                return JSONManager.fromSimpleResultOK();
            }

            return JSONManager.fromError("Unable to store file.");
        } catch (Exception e) {
            log.error("Unable to store file.", e);
            return JSONManager.fromError(e.getLocalizedMessage());
        }
    }

    /**
     * Updates a parameter of a book.
     * @param id Book ID (ISBN)
     * @param field Field to update
     * @param value New value
     * @return JSON Result
     */
    @RequestMapping(value="/storeParameter", method=RequestMethod.GET)
    public @ResponseBody String storeParameter(@RequestParam String id, @RequestParam String field ,@RequestParam String value) {
        if(D) log.debug("Called: storeParameter(["+id+", "+field+", "+value+"])");
        try {
            if(id.equalsIgnoreCase("0")) {
                boolean result = bookDAO.setField(mockBook, field, value);
                if(result)
                    return JSONManager.fromSimpleResultOK();
            } else {
                boolean result = bookDAO.setField(id, field, value);
                if(result)
                    return JSONManager.fromSimpleResultOK();
            }
        } catch (Exception e) {
            return JSONManager.fromError(e.getLocalizedMessage());
        }
        return JSONManager.fromError("Unable to store parameter.");
    }

    /**
     * Stores a book
     * @return JSON Result
     */
    @RequestMapping(value="/storeBook", method=RequestMethod.GET)
    public @ResponseBody String storeBook() {
        if(D) log.debug("Called: storeBook([])");
        try {
            bookDAO.persist(mockBook);
            return JSONManager.fromSimpleResultOK();
        } catch (Exception e) {
            return JSONManager.fromError(e.getLocalizedMessage());
        }
    }

    /**
     * Drops a book
     * @param isbn Book's ISBN
     * @return JSON Result
     */
    @Transactional
    @RequestMapping(value="/dropBook", method=RequestMethod.GET)
    public @ResponseBody String dropBook(@RequestParam String isbn) {
        if(D) log.debug("Called: dropBook(["+isbn+"])");
        try {
            bookDAO.dropByID(isbn);
            return JSONManager.fromSimpleResultOK();
        } catch (Exception e) {
            return JSONManager.fromError(e.getLocalizedMessage());
        }
    }

    //<editor-fold desc="Authors">
    /**
     * Adds an author to a book
     * @param isbn Book's ISBN
     * @param aid Author AID
     * @return JSON Result
     */
    @Transactional
    @RequestMapping(value="/addAuthor", method=RequestMethod.GET)
    public @ResponseBody String addAuthor(@RequestParam String isbn, @RequestParam String aid) {
        if(D) log.debug("Called: addAuthor(["+isbn+", "+aid+"])");
        try {
            Author author = authorDAO.getByID(aid);
            if(author == null) throw new IllegalArgumentException("Author id:"+aid+" not found.");

            if(isbn.equalsIgnoreCase("0")) {
                mockBook.getAuthors().add(author);
                return listAuthors(mockBook.getAuthors());
            } else {
                Book book = bookDAO.getByID(isbn);
                if(book == null) throw new IllegalArgumentException("Book id:"+isbn+" not found.");

                List<Author> authors = book.getAuthors();
                authors.add(author);
                author.getBooks().add(book);

                String list = listAuthors(authors);
                return list;
            }
        } catch (Exception e) {
            return JSONManager.fromError(e.getLocalizedMessage());
        }
    }

    /**
     * Drops an author from a book
     * @param isbn Book's ISBN
     * @param aid Author AID
     * @return JSON Result
     */
    @Transactional
    @RequestMapping(value="/dropAuthor", method=RequestMethod.GET)
    public @ResponseBody String dropAuthor(@RequestParam String isbn, @RequestParam String aid) {
        if(D) log.debug("Called: dropAuthor(["+isbn+", "+aid+"])");
        try {
            Author author = authorDAO.getByID(aid);
            if(author == null) throw new IllegalArgumentException("Author id:"+aid+" not found.");

            if(isbn.equalsIgnoreCase("0")) {
                mockBook.getAuthors().remove(author);
                return listAuthors(mockBook.getAuthors());
            } else {
                Book book = bookDAO.getByID(isbn);
                if(book == null) throw new IllegalArgumentException("Book id:"+isbn+" not found.");

                List<Author> authors = book.getAuthors();
                authors.remove(author);
                author.getBooks().remove(book);

                String list = listAuthors(authors);
                return list;
            }
        } catch (Exception e) {
            return JSONManager.fromError(e.getLocalizedMessage());
        }
    }

    /**
     * Returns a JSON list of authors.
     * @return JSON list.
     */
    @NotNull
    private String listAuthors(List<Author> authors) {
        if(D) log.debug("Called: listAuthors([authors])");
        Map<String, String> idName = new HashMap<String, String>(authors.size());
        for(Author author : authors)
            idName.put(author.getAid()+"", author.getName() + " " + author.getSurname());
        return JSONManager.fromNameIDMap(idName);
    }
    //</editor-fold>

    //<editor-fold desc="Categories">
    /**
     * Adds a category to a book
     * @param isbn Book's ISBN
     * @param cid Category CID
     * @return JSON Result
     */
    @Transactional
    @RequestMapping(value="/addCategory", method=RequestMethod.GET)
    public @ResponseBody String addCategory(@RequestParam String isbn, @RequestParam String cid) {
        if(D) log.debug("Called: addCategory(["+isbn+", "+cid+"])");
        try {
            Category category = categoryDAO.getByID(cid);
            if(category == null) throw new IllegalArgumentException("Category id:"+cid+" not found.");

            if(isbn.equalsIgnoreCase("0")) {
                mockBook.getCategories().add(category);
                return listCategories(mockBook.getCategories());
            } else {
                Book book = bookDAO.getByID(isbn);
                if(book == null) throw new IllegalArgumentException("Book id:"+isbn+" not found.");

                List<Category> categories = book.getCategories();
                categories.add(category);
                category.getBooks().add(book);

                String list = listCategories(categories);
                return list;
            }
        } catch (Exception e) {
            return JSONManager.fromError(e.getLocalizedMessage());
        }
    }

    /**
     * Drops a category from a book
     * @param isbn Book's ISBN
     * @param cid Category ID
     * @return JSON Result
     */
    @Transactional
    @RequestMapping(value="/dropCategory", method=RequestMethod.GET)
    public @ResponseBody String dropCategory(@RequestParam String isbn, @RequestParam String cid) {
        if(D) log.debug("Called: dropCategory(["+isbn+", "+cid+"])");
        try {
            Category category = categoryDAO.getByID(cid);
            if(category == null) throw new IllegalArgumentException("Category id:"+cid+" not found.");

            if(isbn.equalsIgnoreCase("0")) {
                mockBook.getCategories().remove(category);
                return listCategories(mockBook.getCategories());
            } else {
                Book book = bookDAO.getByID(isbn);
                if(book == null) throw new IllegalArgumentException("Book id:"+isbn+" not found.");

                List<Category> categories = book.getCategories();
                categories.remove(category);
                category.getBooks().remove(book);

                String list = listCategories(categories);
                return list;
            }
        } catch (Exception e) {
            return JSONManager.fromError(e.getLocalizedMessage());
        }
    }

    /**
     * Returns a JSON list of categories.
     * @return JSON list.
     */
    @NotNull
    private String listCategories(List<Category> categories) {
        Map<String, String> idName = new HashMap<String, String>(categories.size());
        for(Category category : categories)
            idName.put(category.getCid()+"", category.getName());
        return JSONManager.fromNameIDMap(idName);
    }
    //</editor-fold>

    //<editor-fold desc="Editors">
    /**
     * Adds an editor to a book
     * @param isbn Book's ISBN
     * @param eid Editor EID
     * @return JSON Result
     */
    @Transactional
    @RequestMapping(value="/addEditor", method=RequestMethod.GET)
    public @ResponseBody String addEditor(@RequestParam String isbn, @RequestParam String eid) {
        if(D) log.debug("Called: addEditor(["+isbn+", "+eid+"])");
        try {
            Editor editor = editorDAO.getByID(eid);
            if(editor == null) throw new IllegalArgumentException("Editor id:"+eid+" not found.");

            if(isbn.equalsIgnoreCase("0")) {
                mockBook.getEditors().add(editor);
                return listEditors(mockBook.getEditors());
            } else {
                Book book = bookDAO.getByID(isbn);
                if(book == null) throw new IllegalArgumentException("Book id:"+isbn+" not found.");

                List<Editor> editors = book.getEditors();
                editors.add(editor);
                editor.getBooks().add(book);

                String list = listEditors(editors);
                return list;
            }
        } catch (Exception e) {
            return JSONManager.fromError(e.getLocalizedMessage());
        }
    }

    /**
     * Drops an editor from a book
     * @param isbn Book's ISBN
     * @param eid Editor's ID
     * @return JSON Result
     */
    @Transactional
    @RequestMapping(value="/dropEditor", method=RequestMethod.GET)
    public @ResponseBody String dropEditor(@RequestParam String isbn, @RequestParam String eid) {
        if(D) log.debug("Called: dropEditor(["+isbn+", "+eid+"])");
        try {
            Editor editor = editorDAO.getByID(eid);
            if(editor == null) throw new IllegalArgumentException("Editor id:"+eid+" not found.");

            if(isbn.equalsIgnoreCase("0")) {
                mockBook.getEditors().remove(editor);
                return listEditors(mockBook.getEditors());
            } else {
                Book book = bookDAO.getByID(isbn);
                if(book == null) throw new IllegalArgumentException("Book id:"+isbn+" not found.");

                List<Editor> editors = book.getEditors();
                editors.remove(editor);
                editor.getBooks().remove(book);

                String list = listEditors(editors);
                return list;
            }
        } catch (Exception e) {
            return JSONManager.fromError(e.getLocalizedMessage());
        }
    }

    /**
     * Returns a JSON list of editors.
     * @return JSON list.
     */
    @NotNull
    private String listEditors(List<Editor> editors) {
        Map<String, String> idName = new HashMap<String, String>(editors.size());
        for(Editor editor : editors)
            idName.put(editor.getEid()+"", editor.getName());
        return JSONManager.fromNameIDMap(idName);
    }
    //</editor-fold>

}
