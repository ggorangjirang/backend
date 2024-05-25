import { checkLogin, createNavbar } from "../../useful-functions.js";

// 요소(element), input 혹은 상수
const titleInput = document.querySelector("#titleInput");
const descriptionInput = document.querySelector("#descriptionInput");
const themeSelectBox = document.querySelector("#themeSelectBox");
const imageInput = document.querySelector("#imageInput");
const fileNameSpan = document.querySelector("#fileNameSpan");
const submitButton = document.querySelector("#addCategoryButton");
const registerCategoryForm = document.querySelector("#registerCategoryForm");

checkLogin();
addAllElements();
addAllEvents();

// html에 요소를 추가하는 함수들을 묶어주어서 코드를 깔끔하게 하는 역할임.
async function addAllElements() {
  createNavbar();
}

// 여러 개의 addEventListener들을 묶어주어서 코드를 깔끔하게 하는 역할임.
function addAllEvents() {
  submitButton.addEventListener("click", handleSubmit);
  themeSelectBox.addEventListener("change", handleColorChange);
  imageInput.addEventListener("change", handleImageUpload);
}

// 카테고리 추가하기 - 사진은 AWS S3에 저장, 이후 카테고리 정보를 백엔드 db에 저장.
async function handleSubmit(e) {
  e.preventDefault();

  const title = titleInput.value;
  const description = descriptionInput.value;
  const themeClass = themeSelectBox.value;
  const image = imageInput.files[0];

  // 입력 칸이 비어 있으면 진행 불가
  if (!title || !description) {
    return alert("빈 칸이 없어야 합니다.");
  }

  try {
    const imageKey = await addImageToS3(imageInput, "category");
    const data = { title, description, themeClass, imageKey };

    await Api.post("/categories", data);

    alert(`정상적으로 ${title} 카테고리가 등록되었습니다.`);

    // 폼 초기화
    registerCategoryForm.reset();
    fileNameSpan.innerText = "";
    themeSelectBox.style.backgroundColor = "white";
    themeSelectBox.style.color = "black";
  } catch (err) {
    console.error(err.stack);
    alert(`문제가 발생하였습니다. 확인 후 다시 시도해 주세요: ${err.message}`);
  }
}
