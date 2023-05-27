const header = document.querySelector('.position-fixed');
const shadowClass = 'header-shadow';

window.addEventListener('scroll', () => {
    if (window.scrollY > 0) {
        header.classList.add(shadowClass);
    } else {
        header.classList.remove(shadowClass);
    }
});
