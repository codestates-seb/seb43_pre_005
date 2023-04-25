import styled from "styled-components";

const TagContainer = styled.div`
  border: solid 1px red;
  margin: 1rem;
  width: 20vw;
  height: 20vh;
`;

const Tag = ({ tag }) => {
  //   const parsedDate = new Date(tag.createdAt).toLocaleDateString("ko-kr");

  return (
    <TagContainer>
      {/* <div className="tag-id">{tag.id}</div> */}
      <div className="tag-name">{tag.name}</div>
      <div className="tag-content">{tag.content}</div>
      <div className="tag-questions">{tag.questions}</div>
      <div className="tag-asked">{tag.asked}</div>
    </TagContainer>
  );
};

export default Tag;

//tag에서 axios로 데이터를 받아 온 후
//tags에서 usestate 설정하고
//map으로 뿌려주기?
//x

//데이터를 부모 컴포넌트로부터 전달받고, map()함수를 사용해서
//tag에서 뿌린다
