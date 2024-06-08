// 요소(element)들
const ordersCount = document.querySelector("#ordersCount");
const prepareCount = document.querySelector("#prepareCount");
const deliveryCount = document.querySelector("#deliveryCount");
const completeCount = document.querySelector("#completeCount");
const ordersContainer = document.querySelector("#ordersContainer");
const modal = document.querySelector("#modal");
const modalBackground = document.querySelector("#modalBackground");
const modalCloseButton = document.querySelector("#modalCloseButton");
const deleteCompleteButton = document.querySelector("#deleteCompleteButton");
const deleteCancelButton = document.querySelector("#deleteCancelButton");

let orderIdToDelete;

addAllEvents();

function addAllEvents() {
  modalBackground.addEventListener("click", closeModal);
  modalCloseButton.addEventListener("click", closeModal);
  document.addEventListener("keydown", keyDownCloseModal);
  deleteCompleteButton.addEventListener("click", deleteOrderData);
  deleteCancelButton.addEventListener("click", cancelDelete);
  insertOrders();
}

async function insertOrders() {
  const orders = await Api.get("api/orders");

  const summary = {
    ordersCount: 0,
    prepareCount: 0,
    deliveryCount: 0,
    completeCount: 0,
  };

  for (const order of orders) {
    const { id, totalPrice, createdAt, summaryTitle, status } = order;
    const date = createdAt;

    summary.ordersCount += 1;

    if (status === "DELIVERY_READY") {
      summary.prepareCount += 1;
    } else if (status === "DELIVERING") {
      summary.deliveryCount += 1;
    } else if (status === "DELIVERY_COMPLETE") {
      summary.completeCount += 1;
    }

    ordersContainer.insertAdjacentHTML(
        "beforeend",
        `
      <div class="columns orders-item" id="order-${id}">
        <div class="column is-2">${date}</div>
        <div class="column is-4 order-summary">${summaryTitle}</div>
        <div class="column is-2">${addCommas(totalPrice)}</div>
        <div class="column is-2">
          <div class="select">
            <select id="statusSelectBox-${id}">
              <option class="has-background-danger-light has-text-danger" ${status === "DELIVERY_READY" ? "selected" : ""} value="DELIVERY_READY">
                상품 준비중
              </option>
              <option class="has-background-primary-light has-text-primary" ${status === "DELIVERING" ? "selected" : ""} value="DELIVERING">
                상품 배송중
              </option>
              <option class="has-background-grey-light" ${status === "DELIVERY_COMPLETE" ? "selected" : ""} value="DELIVERY_COMPLETE">
                배송완료
              </option>
            </select>
          </div>
        </div>
        <div class="column is-2">
          <button class="button" id="deleteButton-${id}">주문 취소</button>
        </div>
      </div>
      `
    );

    const statusSelectBox = document.querySelector(`#statusSelectBox-${id}`);
    const deleteButton = document.querySelector(`#deleteButton-${id}`);

    const index = statusSelectBox.selectedIndex;
    statusSelectBox.className = statusSelectBox[index].className;

    statusSelectBox.addEventListener("change", async () => {
      const newStatus = statusSelectBox.value;
      const data = { status: newStatus };

      const index = statusSelectBox.selectedIndex;
      statusSelectBox.className = statusSelectBox[index].className;

      await Api.patch("api/orders", id, data);
    });

    deleteButton.addEventListener("click", () => {
      orderIdToDelete = id;
      openModal();
    });
  }

  ordersCount.innerText = addCommas(summary.ordersCount);
  prepareCount.innerText = addCommas(summary.prepareCount);
  deliveryCount.innerText = addCommas(summary.deliveryCount);
  completeCount.innerText = addCommas(summary.completeCount);
}

async function deleteOrderData(e) {
  e.preventDefault();

  try {
    await Api.delete("api/orders", orderIdToDelete);

    alert("주문 정보가 삭제되었습니다.");

    const deletedItem = document.querySelector(`#order-${orderIdToDelete}`);
    deletedItem.remove();

    orderIdToDelete = "";
    closeModal();
    window.location.reload();
  } catch (err) {
    alert(`주문정보 삭제 과정에서 오류가 발생하였습니다: ${err}`);
  }
}

function cancelDelete() {
  orderIdToDelete = "";
  closeModal();
}

function openModal() {
  modal.classList.add("is-active");
}

function closeModal() {
  modal.classList.remove("is-active");
}

function keyDownCloseModal(e) {
  if (e.keyCode === 27) {
    closeModal();
  }
}
