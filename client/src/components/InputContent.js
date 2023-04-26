import styled from "styled-components";
import { useNavigate } from "react-router-dom";

const InputContent = ({ inputcontent }) => {
  return (
    <>
      <div id={inputcontent.id}></div>
      <div>{inputcontent}</div>
    </>
  );
};

export default InputContent;
