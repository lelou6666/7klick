/**
 * Created by pierre.petersson on 26/10/2014.
 */
$.sound_on = false;
ignore_key_elms = ["#header, #left-panel, #main, div.page-footer, #shortcut, #divSmallBoxes, #divMiniIcons, #divbigBoxes, #voiceModal, script"];
$.viewProfile = "secured/template/profile";
$.viewStartPage = "init";
jsArray = {};
$.ticket=null;
function updateTicket(ticket){
    $.ticket=ticket;
    $.cookie("ticket", $.ticket, { path: '/' });
}
function removeTicket(){
    $.ticket=null;
    $.removeCookie("ticket");
}
function getTicket(){
    return $.cookie("ticket");
}
function pageSetup(){
    $.ticket= getTicket();
    checkURL();
}


/*
$.ajaxSetup({
    //data: {"ticket": getTicket()},
    beforeSend: function (){
        $('#modalLayer').show();
    },
    complete: function () {
        $('#modalLayer').hide();
    }
});
*/


// General error handling
$(document).ajaxError(function (event, jqxhr, settings, thrownError) {

    var errorMessage;
    try {
/*
        if(jqxhr.status == 401){
            removeTicket();
            document.location.href="/profile";
            return;
        }
*/

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

function checkURL() {
        var container;
        var a = location.href.split("#").splice(1).join("#");
        if (!a)try {
            var b = window.document.URL;
            b && b.indexOf("#", 0) > 0 && b.indexOf("#", 0) < b.length + 1 && (a = b.substring(b.indexOf("#", 0) + 1))
        } catch (c) {
        }
        if (container = $("#main-content"), a) {
            $("nav li.active").removeClass("active"), $('nav li:has(a[href="' + a + '"])').addClass("active");
            var d = $('nav a[href="' + a + '"]').attr("title");
            if(location.href.indexOf("#init")<0 ) {
                document.title = d || document.title, loadURL(a + location.search, container)
            }else if(location.href.indexOf("#init")>0 && getTicket()!=null){
                document.title = d || document.title, loadURL(a + location.search, container);
            }
        } else {
            if (getTicket() != undefined) {
                container = $("#main-content");
                window.location.hash = $.viewProfile;
                document.title = d || document.title, loadURL(a + location.search, container);
            } else {
                window.location.hash = $.viewStartPage;
            }
        }

}
function loadScript(a, b) {
    if (jsArray[a])b && b(); else {
        jsArray[a] = !0;
        var c = document.getElementsByTagName("body")[0], d = document.createElement("script");
        d.type = "text/javascript", d.src = a, d.onload = b, c.appendChild(d)
    }
}
function drawBreadCrumb() {
    return;
    /*
     var a = $("nav li.active > a"), b = a.length;
     bread_crumb.empty(), bread_crumb.append($("<li>Home</li>")), a.each(function () {
     bread_crumb.append($("<li></li>").html($.trim($(this).clone().children(".badge").remove().end().text()))), --b || (document.title = bread_crumb.find("li:last-child").text())
     }), a = null
     */
}
function loadURL(a, b) {
    $.ajax({
        type: "GET",
        url: a+"?ticket="+$.ticket,
        dataType: "html",
        cache: !0,
        beforeSend: function () {
            if ($.navAsAjax && $.intervalArr.length > 0 && b[0] == $("#main-content")[0])for (; $.intervalArr.length > 0;)clearInterval($.intervalArr.pop());
            pagefunction = null, b.removeData().html(""), b.html('<h1 class="ajax-loading-animation"><i class="fa fa-cog fa-spin"></i> Loading...</h1>'), b[0] == $("#main-contentF")[0] && ($("body").find("> *").filter(":not(" + ignore_key_elms + ")").empty().remove(), drawBreadCrumb(), $("html").animate({scrollTop: 0}, "fast"))
        }, success: function (a) {
            b.css({opacity: "0.0"}).html(a).delay(50).animate({opacity: "1.0"}, 300), a = null, b = null
        }, error: function () {
            b.html('<h4 class="ajax-loading-error"><i class="fa fa-warning txt-color-orangeDark"></i> Error 404! Page not found.</h4>')
        }, async: !0
    })
}
