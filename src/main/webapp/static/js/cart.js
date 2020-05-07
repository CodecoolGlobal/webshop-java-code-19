function addToCart(productId) {
    fetch(`/cart?action=add&id=${productId}`, {
        method : 'POST'
    } )
        .then( response => response.json())
        .then(data => addToDiv(data));

}

function addToDiv(data) {
    let orderedItems = data["orderedItems"];
    document.getElementById("cart-badge").setAttribute("value", orderedItems);
}

