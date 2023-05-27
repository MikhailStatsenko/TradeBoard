const priceElements = document.querySelectorAll('.price');

priceElements.forEach((priceElement) => {
    const price = parseFloat(priceElement.innerText);

    const formattedPrice = price % 1 === 0 ? price.toFixed(0) : price.toLocaleString('ru-RU', {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    }).replace('.', ',');

    const parts = formattedPrice.split(',');
    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ' ');

    const finalPrice = parts.join(',');

    priceElement.innerText = finalPrice;
});