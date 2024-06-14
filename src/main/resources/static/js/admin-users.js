async function get(url) {
  const response = await fetch(url);
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  return response.json();
}

async function deleteUser(url, email) {
  const response = await fetch(`${url}/${CSS.escape(email)}/cancellation`, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json",
    }
  });
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  return response.json();
}

// 관리자 권한 확인 로직 구현
function checkAdmin() {
  console.log("관리자 권한 확인");
}

// 요소(element), input 혹은 상수
const usersCount = document.querySelector("#usersCount");
const usersContainer = document.querySelector("#usersContainer");
const modal = document.querySelector("#modal");
const modalBackground = document.querySelector("#modalBackground");
const modalCloseButton = document.querySelector("#modalCloseButton");
const deleteCompleteButton = document.querySelector("#deleteCompleteButton");
const deleteCancelButton = document.querySelector("#deleteCancelButton");

checkAdmin();
addAllElements();
addAllEvents();

function addAllElements() {
  insertUsers();
}

function addAllEvents() {
  modalBackground.addEventListener("click", closeModal);
  modalCloseButton.addEventListener("click", closeModal);
  document.addEventListener("keydown", keyDownCloseModal);
  deleteCompleteButton.addEventListener("click", deleteUserData);
  deleteCancelButton.addEventListener("click", cancelDelete);
}

// 페이지 로드 시 실행, 삭제할 회원 email를 전역변수로 관리함
let userEmailToDelete;
async function insertUsers() {
  try {
    const users = await get("/admin/api");

    const summary = {
      usersCount: 0,
    };

    for (const user of users) {
      const { email, name, phoneNumber, address, createdAt, deletedAt } = user;
      const date = createdAt ? new Date(createdAt).toLocaleDateString() : '';

      summary.usersCount += 1;

      usersContainer.insertAdjacentHTML(
          "beforeend",
          `
          <div class="columns orders-item ${deletedAt ? 'is-deleted' : ''}" id="user-${CSS.escape(email)}">
            <div class="column is-2">${date}</div>
            <div class="column is-2 email-column">${email}</div>
            <div class="column is-2">${name}</div>
            <div class="column is-2">${phoneNumber}</div>
            <div class="column is-2">${address}</div>
            <div class="column is-2">
              <button class="button delete-button" data-email="${CSS.escape(email)}" ${deletedAt ? 'disabled' : ''}>탈퇴</button>
            </div>
          </div>
        `
      );
    }

    document.querySelectorAll(".delete-button").forEach(button => {
      button.addEventListener("click", (e) => {
        userEmailToDelete = e.target.getAttribute("data-email");
        openModal();
      });
    });

    usersCount.innerText = summary.usersCount;
  } catch (error) {
    console.error('Error fetching user data:', error);
    alert('회원 정보를 불러오는 중 오류가 발생했습니다.');
  }
}

async function deleteUserData(e) {
  e.preventDefault();

  try {
    await deleteUser("/admin/api", userEmailToDelete);

    alert("회원이 탈퇴 처리되었습니다.");

    const deletedItem = document.querySelector(`#user-${CSS.escape(userEmailToDelete)}`);
    if (deletedItem) {
      deletedItem.classList.add("is-deleted");
      const deleteButton = deletedItem.querySelector(`.delete-button`);
      if (deleteButton) {
        deleteButton.disabled = true;
      }
    }

    userEmailToDelete = "";
    closeModal();
  } catch (err) {
    alert(`회원 탈퇴 처리 과정에서 오류가 발생하였습니다: ${err}`);
  }
}

function cancelDelete() {
  userEmailToDelete = "";
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
