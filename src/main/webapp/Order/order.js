// 의뢰글 작성 완료 버튼 클릭 시
function submitPost() {
  const category = document.getElementById('category').value;
  const title = document.getElementById('title').value;
  const content = document.getElementById('content').value;
  const price = document.getElementById('price').value;

  // 첨부파일 처리 (옵션)
  const file = document.getElementById('file').files[0];
  let fileName = '';
  if (file) {
    fileName = file.name; // 파일 이름 저장
  }

  savePost(category, title, content, price, fileName);
}

// 의뢰글 데이터를 로컬 스토리지에 저장
function savePost(category, title, content, price, fileName) {
  const posts = JSON.parse(localStorage.getItem('posts')) || [];
  const newPost = {
    id: Date.now(), // 고유 ID 생성
    category,
    title,
    content,
    price,
    fileName,
  };
  posts.push(newPost);
  localStorage.setItem('posts', JSON.stringify(posts));

  alert('의뢰글이 작성되었습니다!');
  window.location.href = 'order_list.html'; // 게시판 페이지로 이동
}
