import Layout from "../components/common/Layout";
import { useState } from "react";
import { useCallback } from "react";
import styled from "styled-components";
import Tag from "../components/common/Tag";
import dummyData from "../data/dummyData";

const HeadContainer = styled.div`
  .header-content {
    font-size: 34px;
    margin: 0.5rem;
  }

  .main-content {
    font-size: 14px;
    margin: 0.4rem;
  }

  .content-all {
    display: flex;
    flex-direction: column;
  }

  .box-content {
    margin: 0.4rem;
    border: 1px solid black;
    /* width: 100vw;
    height: 100vh; */
  }

  .tag-container {
    border: 1px solid black;
    width: 80vw;
    height: 80vh;
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    grid-gap: 10px;
  }

  > input {
    margin-top: 1rem;
    margin-left: 0.4rem;
    height: 1.5rem;
  }
`;
const Tags = () => {
  const [tags, setTags] = useState(dummyData);
  const [inputValue, setInputValue] = useState("");
  const [count, setCount] = useState(11);
  const handleChangeInput = (e) => {
    setInputValue(e.target.value);
    const tag = {
      id: count,
      name: "di",
      questions: 1223,
      asked: 1111,
    };
    //input으로 입력할때마다 tag가 생김 (이럼안되지!)
    setTags([...tags, tag]);
    setCount(count + 1);
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
          {tags.map((el) => (
            <Tag key={el.id} tag={el} />
          ))}
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
