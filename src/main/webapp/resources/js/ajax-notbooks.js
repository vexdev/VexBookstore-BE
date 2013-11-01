$(document).ready(function() {

    /**
     * Function used to store parameters
     */
    $('.edit').editable(function(value, settings) {
        var url = baseUrl + '/storeParameter';
        var id = jQuery(this).closest('tr').attr('id');
        var field = jQuery(this).closest('td').attr('class');
        var data = {
            id    : id,
            field : field,
            value : value
        };
        getJSON(url, data, function(result) {});
        return(value);
    }, {
        indicator : 'Saving...',
        tooltip   : 'Click to edit...'
    });

    /**
     * Function used to completely drop a page element
     */
    function dropElement() {
        var bind = jQuery('tr.element a');
        bind.unbind();
        bind.click(function() {
            var id = jQuery(this).closest(".element").attr("id");
            event.preventDefault();
            jQuery(this).unbind();
            var url = baseUrl + '/dropElement';
            var data = { id : id };
            getJSON(url, data, function(result) {
                if(result.result.toLowerCase() === "ok") location.reload();
            });
        });
    }
    /**
     * Function used to add a new element
     */
    function addElement() {
        var bind = jQuery('tr.plus a');
        bind.unbind();
        bind.click(function(event) {
            event.preventDefault();
            jQuery(this).unbind();
            var url = baseUrl + '/storeElement';
            var data = { };
            getJSON(url, data, function(result) {
                if(result.result.toLowerCase() === "ok") location.reload();
            });
        });
    }

    dropElement();
    addElement();

});


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
