import styled from "styled-components";

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

  > input {
    margin-top: 1rem;
    margin-left: 0.4rem;
    height: 1.5rem;
  }
`;

const Tags = () => {
  return (
    <HeadContainer>
      <div className="header-content">Tags</div>
      <div className="main-content">
        A tag is a keyword or label that categorizes your question with other,
        similar questions.<br></br> Using the right tags makes it easier for
        others to find and answer your question.
      </div>
      <input type="text" placeholder="Filter by tag name" />
      <div className="content-all"></div>
    </HeadContainer>
  );
};

export default Tags;
