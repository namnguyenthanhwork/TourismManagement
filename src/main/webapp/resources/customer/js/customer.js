$('.slideFluid').owlCarousel({
    loop: true,
    margin: 10,
    nav: true,
    responsive: {
        0: {
            items: 1
        },
        500: {
            items: 2
        },
        770: {
            items: 3
        },
        1200: {
            items: 4
        }
    }
})
$('.slick-init').slick({
    dots: false,
    infinite: false,
    speed: 300,
    slidesToShow: 4,
    slidesToScroll: 4,
    responsive: [{
        breakpoint: 1024,
        settings: {
            slidesToShow: 3,
            slidesToScroll: 3,
            infinite: true,
        }
    },
        {
            breakpoint: 600,
            settings: {
                slidesToShow: 2,
                slidesToScroll: 2
            }
        },
        {
            breakpoint: 480,
            settings: {
                slidesToShow: 1,
                slidesToScroll: 1
            }
        }
    ]
});
$('.slick-init-banner').slick({
    dots: true,
    infinite: true,
    autoplay: true,
    speed: 300,
    slidesToShow: 1,
    slidesToScroll: 1,
    nav: false
});
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