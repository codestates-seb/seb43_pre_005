import React, { useState } from "react";
import styled from "styled-components";
import logo from "../../assets/images/logo.png";
import { useNavigate } from "react-router-dom";

const HeaderContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 50px;
  padding: 10px 20px;
  background-color: white;
`;

const LogoImage = styled.img`
  height: 30px;
  cursor: pointer;
`;

const SearchContainer = styled.div`
  display: flex;
  align-items: center;
  height: 30px;
  width: 700px;
  border-radius: 5px;
  background-color: #f0f2f5;
`;

const SearchBar = styled.input`
  height: 100%;
  width: 100%;
  padding: 5px 10px;
  border: none;
  background-color: transparent;
  font-size: 16px;
  &:focus {
    outline: none;
  }
`;

const SearchButton = styled.button`
  height: 30px;
  padding: 5px 10px;
  margin-left: 10px;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  color: #f48024;
  background-color: white;
  cursor: pointer;
`;

const ActionContainer = styled.div`
  display: flex;
  align-items: center;
`;

const ActionButton = styled.button`
  margin-left: 10px;
  padding: 5px 10px;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  font-weight: bold;
  color: #f48024;
  background-color: white;
  cursor: pointer;
  &:hover {
    background-color: #e6e6e6;
  }
`;

const Header = () => {
  const navigate = useNavigate();
  const selectMenuHandler = (path) => {
    navigate(path);
  };
  const [searchWord, setSearchWord] = useState("");

  const handleSearchChange = (event) => {
    setSearchWord(event.target.value);
  };

  const handleSearchSubmit = (event) => {
    event.preventDefault();
    navigate(`/search?page=1&word=${searchWord}`);
  };

  return (
    <HeaderContainer>
      <LogoImage
        src={logo}
        alt="Stack Overflow Logo"
        onClick={() => selectMenuHandler("/")}
      />
      <form onSubmit={handleSearchSubmit}>
        <SearchContainer>
          <SearchBar
            type="text"
            placeholder="Search..."
            value={searchWord}
            onChange={handleSearchChange}
          />
          <SearchButton
            type="submit"
            searchWord={searchWord}
            onClick={() => {
              navigate("/search");
            }}
          >
            Search
          </SearchButton>
        </SearchContainer>
      </form>
      <ActionContainer>
        <ActionButton
          onClick={() => {
            window.location.href = "/users/login";
          }}
        >
          Log in
        </ActionButton>
        <ActionButton
          onClick={() => {
            window.location.href = "/users/signup";
          }}
        >
          Sign in
        </ActionButton>
      </ActionContainer>
    </HeaderContainer>
  );
};

export default Header;
