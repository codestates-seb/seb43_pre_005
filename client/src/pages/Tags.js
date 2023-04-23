import Layout from "../components/common/Layout";
import { useState } from "react";
import { useCallback } from "react";
import styled from "styled-components";
import Tag from "../components/common/Tag";
import dummyData from "../data/dummyData";
import Pagination from "../components/Pagination";

const HeadContainer = styled.div`
  .header-content {
    font-size: 34px;
    margin: 0.5rem;
    margin-left: 2rem;
    margin-bottom: 1rem;
  }

  .main-content {
    font-size: 14px;
    margin: 0.4rem;
    margin-left: 2rem;
  }

  .content-all {
    display: flex;
    flex-direction: column;
  }

  .tag-container {
    width: 30vw;
    height: 80vh;
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    grid-gap: 1px;
    transform: scale(0.9);
  }

  > input {
    margin-top: 1rem;
    margin-left: 2rem;
    height: 1.5rem;
    height: 2rem;
  }
`;
const Tags = () => {
  const [tags, setTags] = useState(dummyData);
  const [inputValue, setInputValue] = useState("");
  const [count, setCount] = useState(20);
  const [currentPage, setCurrentPage] = useState(1);
  const [tagsPerPage, setTagsPerPage] = useState(12);
  //tagsPerPage는 tagbox기준! 페이지 버튼아님

  // Calculate the index of the last tag on the current page
  //마지막 페이지 계산 //9
  const indexOfLastTag = currentPage * tagsPerPage;
  // 12 0
  // Calculate the index of the first tag on the current page
  //첫 번째 페이지 : 마지막 페이지에서 tagsPerPage뺌 //0
  const indexOfFirstTag = indexOfLastTag - tagsPerPage;

  // Get the tags for the current page
  const currentTags = tags.slice(indexOfFirstTag, indexOfLastTag);

  // Change page
  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  // const handleChangeInput = (e) => {
  //   setInputValue(e.target.value);
  //   const tag = {
  //     id: count,
  //     name: "di",
  //     questions: 1223,
  //     asked: 1111,
  //   };
  //   setTags([...tags, tag]);
  //   setCount(count + 1);
  // };

  const handleChangeInput = (e) => {
    const inputValue = e.target.value;
    setInputValue(inputValue);
    // Get the filtered tags array
    const filteredTags = dummyData.filter((tag) => {
      const words = inputValue.toLowerCase().split(" ");
      return words.every((word) => {
        return tag.name.toLowerCase().includes(word);
      });
    });

    setTags(filteredTags);
  };

  console.log(tags);

  return (
    <Layout>
      <HeadContainer>
        <div className="header-content">Tags</div>
        <div className="main-content">
          A tag is a keyword or label that categorizes your question with other,
          similar questions.<br></br> Using the right tags makes it easier for
          others to find and answer your question.
        </div>
        <input
          type="text"
          placeholder="Filter by tag name"
          onChange={handleChangeInput}
          value={inputValue}
          autoFocus
        />
        <div className="tag-container">
          {currentTags.map((el) => (
            <Tag key={el.id} tag={el} />
          ))}
        </div>
        <div>
          {tags.length > tagsPerPage && (
            <Pagination
              tagsPerPage={tagsPerPage}
              totalTags={tags.length}
              paginate={paginate}
              currentPage={currentPage}
            />
          )}
        </div>
      </HeadContainer>
    </Layout>
  );
};

export default Tags;

//onchange의 e.target.value값이 들어올 때마다 api를 요청해야한다
//검색했을 때 순서와 상관없이 스펠링이 있으면 검색 되어야 한다

//페이지네이션으로 구현해야 하기 때문에
//dummydata로 tag 내용이 들어오는 지 확인 후
//우선 페이지네이션 없이 map으로 뿌리는것
