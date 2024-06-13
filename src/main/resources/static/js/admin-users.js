async function get(url) {
  const response = await fetch(url);
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  return response.json();
}

async function patch(url, email, data) {
  const response = await fetch(`${url}/${email}`, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });
  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`);
  }
  return response.json();
}

async function deleteUser(url, email) {
  const response = await fetch(`${url}/${email}/delete`, {
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
  // 실제 관리자 권한 확인 로직을 여기에 작성합니다.
  // 예: 쿠키나 로컬스토리지에서 토큰을 확인하거나, 서버에 권한 확인 요청을 보낼 수 있습니다.
  console.log("관리자 권한 확인");
}

// 요소(element), input 혹은 상수
const usersCount = document.querySelector("#usersCount");
const consumersCount = document.querySelector("#consumersCount");
const sellersCount = document.querySelector("#sellersCount");
const usersContainer = document.querySelector("#usersContainer");
const modal = document.querySelector("#modal");
const modalBackground = document.querySelector("#modalBackground");
const modalCloseButton = document.querySelector("#modalCloseButton");
const deleteCompleteButton = document.querySelector("#deleteCompleteButton");
const deleteCancelButton = document.querySelector("#deleteCancelButton");

checkAdmin();
addAllElements();
addAllEvents();

// 요소 삽입 함수들을 묶어주어서 코드를 깔끔하게 하는 역할임.
function addAllElements() {
  insertUsers();
}

// 여러 개의 addEventListener들을 묶어주어서 코드를 깔끔하게 하는 역할임.
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
  const users = await get("/admin/users/api");

  // 총 요약에 활용
  const summary = {
    usersCount: 0,
    consumersCount: 0,
    sellersCount: 0,
  };

  for (const user of users) {
    const { id, email, name, phoneNumber, address, createdAt, deletedAt } = user;
    const date = createdAt;

    summary.usersCount += 1;

    if (user.role.includes('CONSUMER')) {
      summary.consumersCount += 1;
    }

    if (user.role.includes('SELLER')) {
      summary.sellersCount += 1;
    }

    usersContainer.insertAdjacentHTML(
        "beforeend",
        `
        <div class="columns orders-item ${deletedAt ? 'is-deleted' : ''}" id="user-${email}">
          <div class="column is-2">${date}</div>
          <div class="column is-2">${email}</div>
          <div class="column is-2">${name}</div>
          <div class="column is-2">${phoneNumber}</div>
          <div class="column is-2">${address}</div>
          <div class="column is-2">
            <button class="button" id="deleteButton-${email}" ${deletedAt ? 'disabled' : ''}>회원 탈퇴</button>
          </div>
        </div>
      `
    );

    // 요소 선택
    const deleteButton = document.querySelector(`#deleteButton-${email}`);

    // 이벤트 - 탈퇴버튼 클릭 시 Modal 창 띄우고, 동시에, 전역변수에 해당 회원의 email 할당
    deleteButton.addEventListener("click", () => {
      userEmailToDelete = email;
      openModal();
    });
  }

  // 총 요약에 값 삽입
  usersCount.innerText = addCommas(summary.usersCount);
  consumersCount.innerText = addCommas(summary.consumersCount);
  sellersCount.innerText = addCommas(summary.sellersCount);
}

// db에서 회원정보 탈퇴 처리
async function deleteUserData(e) {
  e.preventDefault();

  try {
    await deleteUser("/admin/users/api", userEmailToDelete);

    // 탈퇴 처리 성공
    alert("회원이 탈퇴 처리되었습니다.");

    // 탈퇴 처리한 아이템 화면에서 비활성화
    const deletedItem = document.querySelector(`#user-${userEmailToDelete}`);
    deletedItem.classList.add("is-deleted");
    const deleteButton = deletedItem.querySelector(`#deleteButton-${userEmailToDelete}`);
    deleteButton.disabled = true;

    // 전역변수 초기화
    userEmailToDelete = "";

    closeModal();
  } catch (err) {
    alert(`회원 탈퇴 처리 과정에서 오류가 발생하였습니다: ${err}`);
  }
}

// Modal 창에서 아니오 클릭할 시, 전역 변수를 다시 초기화함.
function cancelDelete() {
  userEmailToDelete = "";
  closeModal();
}

// Modal 창 열기
function openModal() {
  modal.classList.add("is-active");
}

// Modal 창 닫기
function closeModal() {
  modal.classList.remove("is-active");
}

// 키보드로 Modal 창 닫기
function keyDownCloseModal(e) {
  // Esc 키
  if (e.keyCode === 27) {
    closeModal();
  }
}
