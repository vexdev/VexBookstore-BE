package com.vexdev.models;

import com.vexdev.models.interfaces.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oslinux
 * Date: 19/10/13
 * Time: 00:07
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Book implements BaseEntity {
    private String isbn;
    private String name;
    private String description;
    private String url;
    private String imgurl;
    private BigDecimal price;
    private Integer pages;
    private Integer edition;
    private List<Author> authors;
    private List<Category> categories;
    private List<Editor> editors;

    public Book() {
        authors = new ArrayList<Author>();
        categories = new ArrayList<Category>();
        editors = new ArrayList<Editor>();
    }

    @javax.persistence.Column(name = "isbn")
    @Id
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @javax.persistence.Column(name = "name")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @javax.persistence.Column(name = "description")
    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @javax.persistence.Column(name = "url")
    @Basic
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @javax.persistence.Column(name = "imgurl")
    @Basic
    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @javax.persistence.Column(name = "price", precision = 6, scale = 5)
    @Basic
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @javax.persistence.Column(name = "pages")
    @Basic
    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    @javax.persistence.Column(name = "edition")
    @Basic
    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        if (edition != null ? !edition.equals(book.edition) : book.edition != null) return false;
        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (pages != null ? !pages.equals(book.pages) : book.pages != null) return false;
        if (price != null ? !price.equals(book.price) : book.price != null) return false;
        if (url != null ? !url.equals(book.url) : book.url != null) return false;
        if (imgurl != null ? !imgurl.equals(book.imgurl) : book.imgurl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = isbn != null ? isbn.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (imgurl != null ? imgurl.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (pages != null ? pages.hashCode() : 0);
        result = 31 * result + (edition != null ? edition.hashCode() : 0);
        return result;
    }

    @javax.persistence.JoinTable(name = "aubo", catalog = "pwtest", schema = "", joinColumns = @javax.persistence.JoinColumn(name = "isbn", referencedColumnName = "isbn", nullable = false), inverseJoinColumns = @javax.persistence.JoinColumn(name = "aid", referencedColumnName = "aid", nullable = false))
    @ManyToMany(cascade= CascadeType.ALL)
    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @javax.persistence.JoinTable(name = "cabo", catalog = "pwtest", schema = "", joinColumns = @javax.persistence.JoinColumn(name = "isbn", referencedColumnName = "isbn", nullable = false), inverseJoinColumns = @javax.persistence.JoinColumn(name = "cid", referencedColumnName = "cid", nullable = false))
    @ManyToMany
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @javax.persistence.JoinTable(name = "edbo", catalog = "pwtest", schema = "", joinColumns = @javax.persistence.JoinColumn(name = "isbn", referencedColumnName = "isbn", nullable = false), inverseJoinColumns = @javax.persistence.JoinColumn(name = "eid", referencedColumnName = "eid", nullable = false))
    @ManyToMany
    public List<Editor> getEditors() {
        return editors;
    }

    public void setEditors(List<Editor> editors) {
        this.editors = editors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", imgurl='" + imgurl + '\'' +
                ", price=" + price +
                ", pages=" + pages +
                ", edition=" + edition +
                ", authors=" + authors +
                ", categories=" + categories +
                ", editors=" + editors +
                '}';
    }
}
