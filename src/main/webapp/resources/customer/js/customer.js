$(".mda-tour-sort li").click(function () {
        $(this).parents(".mda-box-r").hasClass("active") && $(this).parents(".mda-box-r").removeClass("active")
    }),
    $(window).resize(function () {})
$(document).ready(function () {
    $(".menuSidebar .title").click(function () {
            $(this).parents(".menuSidebar").hasClass("active") ? $(this).parents(".menuSidebar").removeClass("active") : $(this).parents(".menuSidebar").addClass("active")
        }),
        $(".hotlineTop .icon").click(function () {
            $(this).parents(".hotlineTop").hasClass("active") ? $(this).parents(".hotlineTop").removeClass("active") : $(this).parents(".hotlineTop").addClass("active")
        }),
        $(".memberTop .icon").click(function () {
            $(this).parents(".memberTop").hasClass("active") ? $(this).parents(".memberTop").removeClass("active") : $(this).parents(".memberTop").addClass("active")
        }),
        $(".menuToggle .icon").click(function () {
            $(this).parents(".menuToggle").hasClass("active") ? $(this).parents(".menuToggle").removeClass("active") : $(this).parents(".menuToggle").addClass("active")
        }),
        $(window).bind("click", function (e) {
            var t = e.target;
            $(t).parents(".hotlineTop").hasClass("active") || $(".hotlineTop").removeClass("active"),
                $(t).parents(".memberTop").hasClass("active") || $(".memberTop").removeClass("active"),
                $(t).parents(".menuToggle").hasClass("active") || $(".menuToggle").removeClass("active")
        }),
        $(".megaMenu .menuNa ul li").hover(function () {
            $(".megaMenu .menuFo").removeClass("active");
            var e = $(this).attr("data-menu");
            $(".megaMenu .menuFo[data-menu=" + e + "]").addClass("active")
        }),
        $(".myCheckbox").click(function () {
            $(this).hasClass("active") ? ($(this).removeClass("active"),
                $(this).find("input").prop("checked", !1)) : ($(this).addClass("active"),
                $(this).find("input").prop("checked", !0))
        }),
        $("#vnt-huyen-tools .icon").click(function () {
            $(this).parents("#vnt-huyen-tools").hasClass("active") ? $(this).parents("#vnt-huyen-tools").removeClass("active") : $(this).parents("#vnt-huyen-tools").addClass("active")
        })
})
$(document).ready(function () {
    $("body").on("click tap", ".formMenuTour .icon", function () {
            var e = $(this).parents(".col");
            0 == e.find(".wrapPos").length && e.append(wrapPos[e.index()]),
                e.siblings().find(".icon").removeClass("active"),
                e.siblings().find(".wrapPos").removeClass("active"),
                e.find(".wrapPos").hasClass("active") ? ($(this).removeClass("active"),
                    e.find(".wrapPos").removeClass("active")) : ($(this).addClass("active"),
                    e.find(".wrapPos").addClass("active")),
                $(".formMenuTour .wrapPos.active") ? $(".formMenuTour .bgBlack").addClass("active") : $(".formMenuTour .bgBlack").removeClass("active")
        }),
        $("body").on("click tap", ".formMenuTour .bgBlack, .formMenuTour .closez", function () {
            $(".formMenuTour").find(".wrapPos").removeClass("active"),
                $(".formMenuTour .bgBlack").removeClass("active"),
                $(".formMenuTour .icon").removeClass("active")
        }),
        $(window).scroll(function () {
            var i = $(window).scrollTop();
            $(".boxTour").each(function () {
                var e = $(this).innerHeight(),
                    t = $(this).offset().top,
                    a = e + t,
                    n = ".boxDesign2 ul li a[href='#" + $(this).attr("id") + "']";
                t - $("#vnt-header").outerHeight() - 10 < i && a - $("#vnt-header").outerHeight() > i && ($(".boxDesign2 ul li").removeClass("active"),
                    $(n).parents("li").addClass("active"))
            })
        }),
        $(".boxDesign2 ul li a").click(function () {
            $(this).parents(".boxDesign2").hasClass("active") && $(this).parents(".boxDesign2").removeClass("active");
            var e = $(this).attr("href"),
                t = $(e).offset().top - $("#vnt-header").outerHeight() - 5;
            return $("html,body").animate({
                    scrollTop: t
                }, 1e3),
                !1
        }),
        $(".listDay .day .titDay").click(function () {
            if ($(this).parents(".day").hasClass("active"))
                $(this).parents(".day").removeClass("active"),
                $(this).parents(".day").find(".contDay").hide();
            else {
                $(this).parents(".day").addClass("active"),
                    $(this).parents(".day").find(".contDay").show();
                var e = $(this);
                offset = 991 < $(window).innerWidth() ? e.parents(".day").offset().top - 130 : e.parents(".day").offset().top - 30,
                    jQuery("html,body").animate({
                        scrollTop: offset
                    }, 0)
            }
        }),
        $("body").on("click tap", ".mda-box-l-tour i", function () {
            $(".mda-box-l-tour i").removeClass("ative"),
                $(this).addClass("ative"),
                "list" == $(this).attr("data-taget") ? $(".vnt-tour").removeClass("grid") : "colum" == $(this).attr("data-taget") && $(".vnt-tour").addClass("grid")
        }),
        $("body").on("click tap", ".mda-box-top .mda-box-r>span", function () {
            $(this).parents(".mda-box-r").hasClass("active") ? $(this).parents(".mda-box-r").removeClass("active") : $(this).parents(".mda-box-r").addClass("active")
        })
})
$(document).ready(function () {

    /* 1. Visualizing things on Hover - See next part for action on click */
    $('#stars li').on('mouseover', function () {
        var onStar = parseInt($(this).data('value'), 10); // The star currently mouse on

        // Now highlight all the stars that's not after the current hovered star
        $(this).parent().children('li.star').each(function (e) {
            if (e < onStar) {
                $(this).addClass('hover');
            } else {
                $(this).removeClass('hover');
            }
        });

    }).on('mouseout', function () {
        $(this).parent().children('li.star').each(function (e) {
            $(this).removeClass('hover');
        });
    });


    /* 2. Action to perform on click */
    $('#stars li').on('click', function () {
        var onStar = parseInt($(this).data('value'), 10); // The star currently selected
        var stars = $(this).parent().children('li.star');

        for (i = 0; i < stars.length; i++) {
            $(stars[i]).removeClass('selected');
        }

        for (i = 0; i < onStar; i++) {
            $(stars[i]).addClass('selected');
        }

        // JUST RESPONSE (Not needed)
        var ratingValue = parseInt($('#stars li.selected').last().data('value'), 10);
        var msg = "";
        if (ratingValue > 1) {
            msg = "Thanks! You rated this " + ratingValue + " stars.";
        } else {
            msg = "We will improve ourselves. You rated this " + ratingValue + " stars.";
        }
        responseMessage(msg);
    });
});

function responseMessage(msg) {
    $('.success-box').fadeIn(200);
    $('.success-box div.text-message').html("<span>" + msg + "</span>");
}