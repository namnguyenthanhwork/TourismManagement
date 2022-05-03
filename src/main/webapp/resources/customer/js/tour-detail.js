$('#vnt-slide-thumbnail').slick({
    dots: false,
    infinite: true,
    autoplay: true,
    speed: 300,
    slidesToShow: 1,
    slidesToScroll: 1,
    nav: true
});
$(".service-more").click(function (event) {
    $(".service-more-content").toggle();
});
$(".note-more").click(function (event) {
    $(".note-more-content").toggle();
});