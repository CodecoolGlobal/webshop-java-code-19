function addToCart(productId) {
    fetch("/cart?id=" + productId)
        .then( response => response.json())
        .then(data => addToDiv(data));

}

function addToDiv(data) {
    let orderedItems = data["orderedItems"];
    console.log(orderedItems);
    document.getElementById("cart-badge").setAttribute("value", orderedItems);
}

