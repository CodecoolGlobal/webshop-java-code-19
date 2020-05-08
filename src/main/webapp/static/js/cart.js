function errorHandling(error) {
    console.error(error);
}

function modifyCart(action,productId) {
    fetch(`/cart`, {
        method : 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded',},
        body : `action=${action}&id=${productId}`,
    } )
        .then( response => response.json())
        .then(data => modifyDiv(action, data, productId))
        .catch(error => errorHandling(error));

}

function modifyDiv(action, data, productId) {
    let orderedItems = data["orderedItems"];
    switch (action) {
        case "add":
            document.getElementById("cart-badge").setAttribute("value", orderedItems);
            break;
        case "remove":
            let totalPrice = data["totalPrice"];
            let subTotalPrice = data["subTotalPrice"];
            if (orderedItems === 0){
                document.getElementById(`product-${productId}`).remove();
            } else{
                document.getElementById(`product-q-${productId}`).innerText = orderedItems;
                document.getElementById(`product-sub-${productId}`).innerText = subTotalPrice;
            }
            document.getElementById("total-price").innerText = totalPrice;
            break;

        default:
            break;




    }
}

