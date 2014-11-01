/**
 * Created by pierre.petersson on 26/10/2014.
 */
$.sound_on = false;
$.ajaxSetup({
    data: {"ticket": $("#ticket").val()},
    beforeSend: function () {
        $('#modalLayer').show();
    },
    complete: function () {
        $('#modalLayer').hide();
    },
    success: function () {
    }

});

// General error handling
$(document).ajaxError(function (event, jqxhr, settings, thrownError) {
    var errorMessage;
    try {
        if(jqxhr.responseJSON!=undefined) {
            errorMessage = jqxhr.responseJSON.cirrusError.userMessage;
        }else{
            errorMessage = $(".errorMessageUser", jqxhr.responseText).text();
        }

        $.bigBox({
            title: "Error information",
            content: errorMessage,
            color: "#lightblue",
            //timeout: 6000,
            icon: "fa fa-warning shake animated",
            number: "1"
        });
    } catch (ex) {
        errorMessage = ex.toString();
    }
});

// Ajax loads popover content, with attribute data-poload set.
$('*[data-poload]').bind('click', function () {
    var e = $(this);
    e.unbind('click');
    $.get(e.data('poload'), function (d) {
        e.popover({content: d, html: "true", placement: "bottom", container: "body"}).popover('show');
        $(".popover").css("z-index", "1000000");
    });
});

// Ajax content loader, all elements containing data-content-load attribute will ajax load content to #main-content
$('*[data-content-load]').bind('click', function () {
    var e = $(this);
    e.unbind('click');
    $.get(e.data('content-load'), function (d) {
        $("#main-content").html(d);
    });
});

