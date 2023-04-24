import styled from "styled-components";

const PaginationContainer = styled.div`
  display: flex;
  justify-content: flex-end;
  margin-top: 1rem;

  > button {
    margin: 0 1rem;
    border: none;
    background-color: #eee;
    color: #444;
    padding: 0.5rem 1rem;
    cursor: pointer;
    font-size: 1rem;
    border-radius: 0.25rem;
    transition: all 0.3s ease;

    &:hover:not(:disabled) {
      background-color: #ddd;
    }

    &:disabled {
      opacity: 0.5;
      cursor: not-allowed;
    }

    &.active {
      background-color: #f58025;
      color: #fff;
    }
  }
`;

const Pagination = ({ tagsPerPage, totalTags, paginate, currentPage }) => {
  const pageNumbers = [];

  for (let i = 1; i <= Math.ceil(totalTags / tagsPerPage); i++) {
    pageNumbers.push(i);
  }

  // 현재 페이지가 속한 페이지 그룹 구하기
  const currentGroup = Math.ceil(currentPage / 5);

  // 페이지 그룹의 시작 페이지 번호 구하기
  const startPage = (currentGroup - 1) * 5 + 1;

  // 페이지 그룹의 끝 페이지 번호 구하기
  const endPage =
    startPage + 4 > pageNumbers.length ? pageNumbers.length : startPage + 4;

  // 이전 페이지 버튼 클릭 이벤트
  const handlePrevClick = () => {
    if (currentPage > 1) {
      paginate(currentPage - 1);
    }
  };

  // 다음 페이지 버튼 클릭 이벤트
  const handleNextClick = () => {
    if (currentPage < Math.ceil(totalTags / tagsPerPage)) {
      paginate(currentPage + 1);
    }
  };

  return (
    <PaginationContainer>
      <button onClick={handlePrevClick} disabled={currentPage <= 1}>
        이전
      </button>
      {pageNumbers.slice(startPage - 1, endPage).map((number) => (
        <button
          key={number}
          onClick={() => paginate(number)}
          className={currentPage === number ? "active" : ""}
        >
          {number}
        </button>
      ))}
      <button
        onClick={handleNextClick}
        disabled={currentPage >= Math.ceil(totalTags / tagsPerPage)}
      >
        다음
      </button>
    </PaginationContainer>
  );
};

export default Pagination;
