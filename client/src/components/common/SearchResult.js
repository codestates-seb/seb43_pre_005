import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import axios from "axios";
import Layout from "./Layout";

const SearchResult = () => {
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const searchWord = searchParams.get("word");
  const page = searchParams.get("page");

  const [searchResults, setSearchResults] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/search?page=${page}&word=${searchWord}`
        );
        setSearchResults(response.data);
      } catch (error) {
        console.error(error);
      }
    };
    fetchData();
  }, [page, searchWord]);

  if (searchResults.length === 0) {
    return (
      <Layout>
        <p>검색 결과가 없습니다.</p>
      </Layout>
    );
  }

  return (
    <Layout>
      <ul>
        {searchResults.map((result) => (
          <li key={result.id}></li>
        ))}
      </ul>
    </Layout>
  );
};

export default SearchResult;
