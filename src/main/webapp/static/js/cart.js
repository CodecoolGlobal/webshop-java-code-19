function addToCart(productId) {
    fetch("/cart?id=" + productId)
        .then( response => response.json())
        .then(data => console.log(data));

}