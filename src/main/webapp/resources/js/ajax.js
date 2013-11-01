/**
 * Created with IntelliJ IDEA.
 * User: oslinux
 * Date: 29/10/13
 * Time: 12:53
 * To change this template use File | Settings | File Templates.
 */


/**  JEDITABLE  **/

$(document).ready(function() {

    /**
     * Function used to store parameters
     */
    $('.edit').editable(function(value, settings) {
        var url = '/books/storeParameter';
        var completeID = $(this).attr('id');
        var splitted = completeID.split("_");
        var data = {
            id    : splitted[1],
            field : splitted[0],
            value : value
        }
        $.getJSON(url, data, function(result) {});
        return(value);
    }, {
        indicator : 'Saving...',
        tooltip   : 'Click to edit...'
    });

    /**
     * Function used to add an author
     */
    $('.edit-authors').editable(function(value, settings) {
        var isbn = jQuery(this).closest(".book").attr("id");
        var url = '/books/addAuthor';
        var data = { isbn : isbn, aid : value };
        var list = jQuery(this).siblings(".list");
        getJSON(url, data, function(result) {
            if(result) reloadList(list, result);
        });
        return("Add author");
    }, {
        data   : authors,
        type   : 'select',
        submit : 'OK'
    });

    /**
     * Function used to add a category
     */
    $('.edit-categories').editable(function(value, settings) {
        var isbn = jQuery(this).closest(".book").attr("id");
        var url = '/books/addCategory';
        var data = { isbn : isbn, cid : value };
        var list = jQuery(this).siblings(".list");
        getJSON(url, data, function(result) {
            if(result) reloadList(list, result);
        });
        return("Add category");
    }, {
        data   : categories,
        type   : 'select',
        submit : 'OK'
    });

    /**
     * Function used to add an editor
     */
    $('.edit-editors').editable(function(value, settings) {
        var isbn = jQuery(this).closest(".book").attr("id");
        var url = '/books/addEditor';
        var data = { isbn : isbn, eid : value };
        var list = jQuery(this).siblings(".list");
        getJSON(url, data, function(result) {
            if(result) reloadList(list, result);
        });
        return("Add editor");
    }, {
        data   : editors,
        type   : 'select',
        submit : 'OK'
    });

    attachToHTML();
});

/**
 * -------- FUNCTIONS USED TO DROP ELEMENTS
 */
/**
 * Function used to drop an author
 */
function dropAuthor() {
    var bind = jQuery('td.authors ul.list a');
    bind.unbind();
    bind.click(function(){
        event.preventDefault();
        var isbn = jQuery(this).closest(".book").attr("id");
        var list = jQuery(this).closest(".list");
        var value = jQuery(this).closest("li").attr("class");
        var url = '/books/dropAuthor';
        var data = { isbn : isbn, aid : value };
        getJSON(url, data, function(result) {
            if(result) reloadList(list, result);
        });
    });
}
/**
 * Function used to drop a Category
 */
function dropCategory() {
    var bind = jQuery('td.categories ul.list a');
    bind.unbind();
    bind.click(function(){
        event.preventDefault();
        jQuery(this).unbind();
        var isbn = jQuery(this).closest(".book").attr("id");
        var list = jQuery(this).closest(".list");
        var value = jQuery(this).closest("li").attr("class");
        var url = '/books/dropCategory';
        var data = { isbn : isbn, cid : value };
        getJSON(url, data, function(result) {
            if(result) reloadList(list, result);
        });
    });
}
/**
 * Function used to drop an editor
 */
function dropEditor() {
    var bind = jQuery('td.editors ul.list a');
    bind.unbind();
    bind.click(function(){
        event.preventDefault();
        jQuery(this).unbind();
        var isbn = jQuery(this).closest(".book").attr("id");
        var list = jQuery(this).closest(".list");
        var value = jQuery(this).closest("li").attr("class");
        var url = '/books/dropEditor';
        var data = { isbn : isbn, eid : value };
        getJSON(url, data, function(result) {
            if(result) reloadList(list, result);
        });
    });
}
/**
 * Function used to completely drop a page element
 */
function dropElement() {
    var bind = jQuery('tbody>tr a.drop');
    bind.unbind();
    bind.click(function() {
        var isbn = jQuery(this).closest(".book").attr("id");
        event.preventDefault();
        jQuery(this).unbind();
        var url = '/books/dropBook';
        var data = { isbn : isbn };
        getJSON(url, data, function(result) {
            if(result.result.toLowerCase() === "ok") location.reload();
        });
    });
}
/**
 * Function used to add a new element
 */
function addElement() {
    var bind = jQuery('tbody>tr.plus a.add_new');
    bind.unbind();
    bind.click(function(event) {
        event.preventDefault();
        jQuery(this).unbind();
        var url = '/books/storeBook';
        var data = { };
        getJSON(url, data, function(result) {
            if(result.result.toLowerCase() === "ok") location.reload();
        });
    });
}

/**
 * -------- FUNCTIONS USED FOR FILE UPLOAD
 */

$(document).ready(function () {
    $(function () {
        $('.fileupload').fileupload({
            sequentialUploads: true,
            dataType: 'json',
            done: function (e, result) {
                cons
                if(result.result.toLowerCase() === "ok") location.reload();
            }
        });
    });
});

/**  WEB PAGE RELOAD  **/

function reloadList(jQlist, list) {
    if(list) {
        var html = "";
        for(var i=0; i<list.length; i++) {
            html += '<li class="';
            html += list[i].id;
            html += '"><a href="#"><span class="glyphicon glyphicon-minus"></span></a><span>';
            html += list[i].name;
            html += '</span></li>';
        }
        jQlist.html(html);
        attachToHTML();
    }
}

/**  USEFUL FUNCTIONS  **/

/**
 * This function is used to attach other functions to the HTML, and to reattach them in event of a page reload.
 */
function attachToHTML() {
    dropAuthor();
    dropCategory();
    dropEditor();
    dropElement();
    addElement();
}

function getJSON(url, data, callback) {
    jQuery.getJSON(url, data, function(result) {
        if(result && result.error) {
            console.log(result.error);
            callback(null);
        } else {
            callback(result);
        }
    });
}