document.addEventListener('DOMContentLoaded', function() {
    const links = document.querySelectorAll('.dash__f-list li a');
    const currentPage = window.location.href.substring(window.location.href.lastIndexOf('/') + 1);
    let activeFound = false;

    links.forEach(link => {
        const linkHref = link.getAttribute('href');


        if (linkHref === currentPage) {
            link.classList.add('dash-active');
            activeFound = true;
        } else {
            link.classList.remove('dash-active');
        }


        link.addEventListener('click', function() {
            links.forEach(l => l.classList.remove('dash-active'));
            this.classList.add('dash-active');
        });
    });

    if (!activeFound && links.length > 0) {
        links[0].classList.add('dash-active');
    }
});
