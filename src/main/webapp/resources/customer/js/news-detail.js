$('.mid-content').owlCarousel({
    loop: true,
    margin: 10,
    nav: true,
    autoplay: true,
    responsive: {
        0: {
            items: 1,
            nav: true
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