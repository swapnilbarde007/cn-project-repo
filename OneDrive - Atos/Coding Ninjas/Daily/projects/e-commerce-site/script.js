let productArray = [];

class Product {
  id;
  title;
  price;
  images;

  constructor(id, title, price, images) {
    this.id = id;
    this.title = title;
    this.price = price;
    this.images = images;
  }
}

class LineItemId {
  static currLineItemId = 0;
}

class CartLineItem {
  cartLineItemId;
  product;
  quantity;
  itemTotal;

  constructor(product, quantity, itemTotal) {
    LineItemId.currLineItemId++;
    this.cartLineItemId = LineItemId.currLineItemId;
    this.product = product;
    this.quantity = quantity;
    this.itemTotal = itemTotal;
  }
}

let cartLineItems = [];

async function getProductList() {
  try {
    const serverResp = await fetch("https://dummyjson.com/products");
    const respJson = await serverResp.json();
    console.log(respJson);
    respJson.products.forEach((product) => {
      const product1 = new Product(
        product.id,
        product.title,
        product.price,
        product.images
      );
      productArray.push(product1);
    });
    // const productsContainer = document.getElementById("productsContainer");
    // while (productsContainer.hasChildNodes) {
    //   productsContainer.remove(productsContainer.firstChild);
    // }
    populatePorductDOM();
  } catch (e) {
    console.log(e);
  }
}

function addToCart(cartLineItem) {
  const found = cartLineItems.findIndex((item) => {
    return item.product.id === cartLineItem.product.id;
  });
  if (found == -1) {
    cartLineItem.itemTotal = cartLineItem.product.price;
    cartLineItems.push(cartLineItem);
  } else {
    cartLineItems[found].quantity++;
    cartLineItems[found].itemTotal =
      cartLineItems[found].product.price * cartLineItems[found].quantity;
  }
  //cartLineItems.push(cartLineItem);
}

function populatePorductDOM() {
  const productsContainer = document.getElementById("productsContainer");

  console.log("Product DOM Loaded: " + productArray);
  productArray.forEach((product) => {
    //     <div id="product1" class="product-tile">
    const productParent = document.createElement("div");
    productParent.id = "_" + product.id;
    productParent.classList.add("product-tile");

    //     <div id="img-section">
    const imgSec = document.createElement("div");
    imgSec.id = "img-section";
    //         <div id="btnLeft" class="toggleBtn">
    const btnLeft = document.createElement("div");
    btnLeft.id = "btnLeft";
    btnLeft.classList.add("toggleBtn");

    //             <h3>&lt;</h3>
    const lessThan = document.createElement("h3");
    lessThan.textContent = "<";
    //         </div>
    btnLeft.append(lessThan);

    //         <img id="image-tile" src="https://cdn.dummyjson.com/product-images/7/3.jpg">
    const imageTile = document.createElement("img");
    imageTile.id = "image-tile";
    imageTile.src = product.images[0];

    //         <div id="btnRight" class="toggleBtn">
    const btnRight = document.createElement("div");
    btnRight.id = "btnRight";
    btnRight.classList.add("toggleBtn");

    //             <h3>&gt;</h3>
    const greaterThan = document.createElement("h3");
    greaterThan.textContent = ">";

    //         </div>
    btnRight.appendChild(greaterThan);
    const imageTileCurrIdx = document.createElement("div");
    imageTileCurrIdx.id = "imageTileCurrIdx";
    imageTileCurrIdx.style = "display:none";
    imageTileCurrIdx.textContent = "0";
    imgSec.appendChild(imageTileCurrIdx);
    imgSec.appendChild(btnLeft);
    imgSec.appendChild(imageTile);
    imgSec.appendChild(btnRight);
    //     </div>
    btnLeft.addEventListener("click", () => {
      const repsImage = toggleImage(
        Number(imageTileCurrIdx.textContent),
        "prev",
        product
      );
      imageTile.src = repsImage.src;
      imageTileCurrIdx.textContent = repsImage.newIdx;
    });

    btnRight.addEventListener("click", () => {
      const repsImage = toggleImage(
        Number(imageTileCurrIdx.textContent),
        "next",
        product
      );
      imageTile.src = repsImage.src;
      imageTileCurrIdx.textContent = repsImage.newIdx;
    });

    //     <div id="details-sec">
    const detailsSec = document.createElement("div");
    detailsSec.id = "details-sec";

    //       <h3 id="productName">iPhone 15</h3>
    const productName = document.createElement("h4");
    productName.id = "productName";
    productName.textContent = product.title;
    //       <h3 id="productPrice">$549</h3>
    const productPice = document.createElement("h3");
    productPice.id = "productPrice";
    productPice.textContent = "$" + product.price;
    //       <button id="btnAddToCart">Add to Cart</button>

    const btnAddToCart = document.createElement("button");
    btnAddToCart.id = "btnAddToCart";
    btnAddToCart.textContent = "Add to Cart";

    btnAddToCart.addEventListener("click", () => {
      const cartLineItem = new CartLineItem(product, 1, 0);
      addToCart(cartLineItem);
    });
    //     </div>
    detailsSec.appendChild(productName);
    detailsSec.appendChild(productPice);
    detailsSec.appendChild(btnAddToCart);
    // </div>
    productParent.appendChild(imgSec);
    productParent.appendChild(detailsSec);

    productsContainer.appendChild(productParent);
  });
}
function toggleImage(currIdx, operation, product) {
  if (currIdx === 0) {
    if (operation === "prev") {
      return {
        src: product.images[product.images.length - 1],
        newIdx: product.images.length - 1,
      };
    } else {
      return {
        src: product.images[currIdx + 1],
        newIdx: currIdx + 1,
      };
    }
  }
  if (currIdx === product.images.length - 1) {
    if (operation === "next") {
      return { src: product.images[0], newIdx: 0 };
    } else {
      return { src: product.images[currIdx - 1], newIdx: currIdx - 1 };
    }
  }
  if (operation === "prev") {
    return { src: product.images[currIdx - 1], newIdx: currIdx - 1 };
  } else {
    //next
    return { src: product.images[currIdx + 1], newIdx: currIdx + 1 };
  }
}

function displayCart() {
  const productContainer = document.getElementById("productsContainer");
  productContainer.style.display = "none";
  const cartTable = document.getElementById("tBody");

  while (cartTable.hasChildNodes()) {
    cartTable.removeChild(cartTable.firstChild);
  }
  let trItem;
  cartLineItems.forEach((item) => {
    //   <tr>
    trItem = document.createElement("tr");
    //   <td>1</td>
    const lineItemId = document.createElement("td");
    lineItemId.textContent = item.cartLineItemId;

    //   <td>Apple iPhone 15</td>
    const lineItemTitle = document.createElement("td");
    lineItemTitle.textContent = item.product.title;

    //   <td>$550</td>
    const lineItemPrice = document.createElement("td");
    lineItemPrice.textContent = item.product.price;

    //   <td>1</td>
    const lineItemQty = document.createElement("td");

    const qtyCtrl = document.createElement("div");
    qtyCtrl.classList.add("qty-ctrl");
    qtyCtrl.id = `_${item.cartLineItemId}`;

    const addQtyBtn = document.createElement("button");
    addQtyBtn.textContent = "+";

    addQtyBtn.addEventListener("click", () => {
      addQty(item.cartLineItemId);
    });

    const qtyLbl = document.createElement("h4");
    qtyLbl.textContent = item.quantity;

    const reduceQtyBtn = document.createElement("button");
    reduceQtyBtn.textContent = "-";
    reduceQtyBtn.addEventListener("click", () => {
      reduceQty(item.cartLineItemId);
    });

    qtyCtrl.appendChild(addQtyBtn);
    qtyCtrl.appendChild(qtyLbl);
    qtyCtrl.appendChild(reduceQtyBtn);

    lineItemQty.appendChild(qtyCtrl);

    //   <td>$550</td>

    const lineItemTotalPrice = document.createElement("td");
    lineItemTotalPrice.textContent = item.itemTotal;
    // </tr>

    trItem.appendChild(lineItemId);
    trItem.appendChild(lineItemTitle);
    trItem.appendChild(lineItemPrice);
    trItem.appendChild(lineItemQty);
    trItem.appendChild(lineItemTotalPrice);
    cartTable.appendChild(trItem);
  });

  const trItemTotal = document.createElement("tr");
  //   <td>1</td>
  const tdItemTotal = document.createElement("td");

  const totalAmt = cartLineItems.reduce((acc, item) => {
    return acc + item.itemTotal;
  }, 0);
  const lblTdItemTotal = document.createElement("td");
  lblTdItemTotal.textContent = "Grand Total";
  tdItemTotal.setAttribute("colspan", 4);
  tdItemTotal.classList.add("grand-total");
  lblTdItemTotal.classList.add("grand-total");
  tdItemTotal.textContent = totalAmt;
  trItemTotal.appendChild(lblTdItemTotal);
  trItemTotal.appendChild(tdItemTotal);

  cartTable.appendChild(trItemTotal);
}

function main() {
  //Init Product Array
  const resp = getProductList();
  const cartBtn = document.getElementById("cartBtn");
  cartBtn.addEventListener("click", () => {
    displayCart();
  });
  const checkoutBtn = document.getElementById("checkoutBtn");
  checkoutBtn.addEventListener("click", () => {
    alert("Redirecting to payment gateway...");
  });
}

function reduceQty(lineItemId) {
  const found = cartLineItems.findIndex((item) => {
    return item.cartLineItemId === lineItemId;
  });
  if (found != -1) {
    if (cartLineItems[found].quantity === 1) {
      cartLineItems.splice(found, 1);
    } else {
      cartLineItems[found].itemTotal =
        cartLineItems[found].itemTotal - cartLineItems[found].product.price;
      --cartLineItems[found].quantity;
    }
    displayCart();
  }
}

function addQty(lineItemId) {
  const found = cartLineItems.findIndex((item) => {
    return item.cartLineItemId === lineItemId;
  });
  if (found != -1) {
    cartLineItems[found].itemTotal =
      cartLineItems[found].itemTotal + cartLineItems[found].product.price;
    ++cartLineItems[found].quantity;
    displayCart();
  }
}

main();
