document.querySelector('.search-btn').addEventListener('click', () => {
    const searchText = document.querySelector('.search-txt').value;
    alert(`검색어: ${searchText}`);
});
