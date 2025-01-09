// 의뢰글 데이터를 불러와 게시판에 표시
function loadPosts() {
  const posts = JSON.parse(localStorage.getItem('posts')) || [];
  const tbody = document.querySelector('#orderTable tbody');

  // 기존 내용 초기화
  tbody.innerHTML = '';

  if (posts.length === 0) {
    tbody.innerHTML = '<tr><td colspan="5">등록된 의뢰글이 없습니다.</td></tr>';
    return;
  }

  // 의뢰글 데이터 표시
  posts.forEach((post) => {
    const row = document.createElement('tr');
    row.innerHTML = `
      <td>${post.category}</td>
      <td>${post.title}</td>
      <td>${post.content}</td>
      <td>${post.price}</td>
      <td>${post.fileName || '없음'}</td>
    `;
    tbody.appendChild(row);
  });
}

// 페이지 로드 시 의뢰글 로드
document.addEventListener('DOMContentLoaded', loadPosts);
