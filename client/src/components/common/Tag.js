import styled from "styled-components";
import { useNavigate } from "react-router-dom";

const TagContainer = styled.div`
  border: solid 1px gray;
  border-radius: 5px;
  margin: 1rem;
  width: 21vw;
  height: 20vh;
  cursor: pointer;

  .tag-name {
    border-radius: 10px;
    padding-right: 3px;
    padding-left: 10px;
    padding-top: 4px;
    padding-bottom: 4px;
    background-color: #e1ecf4;
    width: 50px;
    display: flex;
    margin-top: 0.9rem;
    margin-left: 1rem;
    height: 100%;
    font-size: 1.1rem;
    font-weight: normal;
    font-color: #5050ff;
    text-align: center;

    &:hover {
      background-color: #ddd;
      cursor: pointer;
    }
  }
  .tag-content {
    display: flex;
    align-items: center;
    margin-left: 0.4rem;
    margin-top: 1rem;
  }
`;

const Tag = ({ tag }) => {
  // const parsedDate = new Date(tag.createdAt).toLocaleDateString("ko-kr");

  const navigate = useNavigate();

  const handleOneClick = () => {
    navigate(`/questions/tag_name`);
  };
  if (!tag) {
    return null; // tag 객체가 없으면 null 반환
  }
  const description =
    tag.description.length > 100
      ? tag.description.slice(0, 150) + "..."
      : tag.description;
  return (
    <TagContainer onClick={handleOneClick}>
      <div className="tag-id">
        <div className="tag-name">{tag.name}</div>
        <div className="tag-content">{description}</div>
      </div>
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
