import styled from "styled-components";
import { useNavigate } from "react-router-dom";

const QuestionContainer = styled.div`
  position: relative;
  border: solid 1px gray;
  margin: 0.5rem;
  width: 50vw;
  height: 15vh;
  padding: 1rem;
  cursor: pointer;

  .question-title {
    font-weight: bold;
    display: flex;
    margin-bottom: 1rem;
  }

  .name-box {
    position: relative;
    display: flex;
    align-items: right;
    justify-content: right;
    margin-top: 3rem;

    .name-sub {
      display: flex;
    }

    .question-tag {
      display: flex;
      position: absolute;
      bottom: 0;
      left: 0;
      border-radius: 5px;
      color: black;
      cursor: pointer;
      padding: 0.3rem;
    }

    .question-tag > div {
      border: none;
      margin-right: 1rem;
      background-color: #baaad7;
      padding: 0.4rem;
      border-radius: 15px;
      color: beige;
      transition: background-color 0.3s;
    }

    .question-tag > div:hover {
      background-color: #ad9aee;
    }
    .question-tag > span {
      margin-right: 1rem;
    }

    .question-name {
      position: relative;
      margin-left: 30rem;
      margin-top: 0.5rem;
      font-weight: bold;
    }

    .question-createAt {
      margin-top: 0.5rem;
      margin-left: 1rem;
    }
  }
`;

const Question = ({ question }) => {
  const { title, content, tag, name, createdAt } = question;

  const navigate = useNavigate();
  function handleDummyClick(id) {
    const newUrl = `/questions/${id}`; // 경로와 id 값을 조합하여 새로운 URL 생성
    navigate(newUrl); // useNavigate Hook을 사용하여 새로운 URL로 이동
  }

  // const handleOneClick = () => {
  //   navigate(`/questions/question_id`);
  // };
  const tags = Array.isArray(tag) ? tag.slice(0, 5) : [];
  //밑의 부분은 현재 시간을 가져오는 함수다. 심심해서 그냥 만들어봤따
  // const createdAt = new Date().toLocaleString();
  return (
    <QuestionContainer>
      <div
        className="question-id"
        onClick={() => handleDummyClick(question.id)}
      >
        <div className="question-title">{title}</div>
        <div className="question-content">{content}</div>
        <div className="name-box">
          <div className="question-tag">
            {/* tag가 배열이 아닐 때의 조건도 추가해야함 최소 0개에서 최대 5개까지 허용가능범위*/}
            {/* tag가 배열이 아니라면 빈칸을 렌더링 하고, 배열이라면 map으로 뿌리고 span으로 감싼다 */}
            {/* 필터링 기준이 1번인지 2번인지에 따라 주석 해제할것 */}
            {/* {!Array.isArray(tag)
              ? ""
              : tag.map((el) => (
                  <div>
                    <span>{el}</span>
                  </div>
                ))} */}
            {tags.map((el) => (
              <div>
                <span>{el}</span>
              </div>
            ))}
          </div>
          <div className="name-sub">
            <div className="question-name">{name}</div>
            <div className="question-createAt">{createdAt}</div>
          </div>
        </div>
      </div>
    </QuestionContainer>
  );
};

export default Question;
