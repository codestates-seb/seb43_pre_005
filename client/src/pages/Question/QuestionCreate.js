import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import Layout from "../../components/common/Layout";
import { usePostData } from "../../customhook/useDataPost";

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 80%;
  margin: 0 auto;
`;

const Title = styled.h1`
  font-size: 32px;
  font-weight: bold;
  margin-bottom: 20px;
`;

const Form = styled.form`
  display: flex;
  flex-direction: column;
`;

const Label = styled.label`
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 10px;
`;

const Input = styled.input`
  height: 40px;
  padding: 5px 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 18px;
  margin-bottom: 20px;
`;

const TextArea = styled.textarea`
  height: 200px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 18px;
  margin-bottom: 20px;
`;

const Select = styled.select`
  height: 40px;
  padding: 5px 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 18px;
  margin-bottom: 20px;
`;

const SubmitButton = styled.button`
  height: 50px;
  background-color: #f48024;
  color: white;
  font-size: 20px;
  font-weight: bold;
  border: none;
  border-radius: 5px;
  cursor: pointer;
`;

const QuestionCreate = () => {
  const [title, setTitle] = useState("");
  const [body, setBody] = useState("");
  const [tag, setTag] = useState("");
  const [postData, setPostData] = useState({ title: "", body: "" });
  const {
    loading,
    error,
    postData: sendPostData,
  } = usePostData("http://localhost:3001/qsdummyData");

  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log(title, body, tag);
    sendPostData(postData).then((data) => {
      console.log("Post data sent:", data);
    });
    navigate("/");
  };

  return (
    <Layout>
      <Container>
        <Title>Ask a Question</Title>
        <Form onSubmit={handleSubmit}>
          <Label htmlFor="title">Title</Label>
          <Input
            type="text"
            id="title"
            value={postData.title}
            onChange={(event) =>
              setPostData({ ...postData, title: event.target.value })
            }
          />
          <Label htmlFor="body">Body</Label>
          <TextArea
            id="body"
            value={postData.content}
            onChange={(event) =>
              setPostData({ ...postData, content: event.target.value })
            }
          />
          <Label htmlFor="tag">Tag</Label>
          <Select
            id="tag"
            value={postData.tag}
            onChange={(event) =>
              setPostData({ ...postData, tag: event.target.value })
            }
          >
            <option value="">Select a tag</option>
            <option value="react">React</option>
            <option value="javascript">JavaScript</option>
            <option value="html">HTML</option>
            <option value="css">CSS</option>
          </Select>
          <SubmitButton type="submit" disabled={loading}>
            {loading ? "Loading..." : "Submit"}
          </SubmitButton>
          {error && <p>Error: {error.message}</p>}
        </Form>
      </Container>
    </Layout>
  );
};

export default QuestionCreate;
