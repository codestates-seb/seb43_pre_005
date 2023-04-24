import React from "react";
import styled from "styled-components";
import logo from "../../assets/images/logo.png";

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
  padding: 5px 10px;git
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
  return (
    <HeaderContainer>
      <LogoImage src={logo} alt="Stack Overflow Logo" />
      <SearchContainer>
        <SearchBar type="text" placeholder="Search..." />
        <SearchButton type="submit">Search</SearchButton>
      </SearchContainer>
      <ActionContainer>
        <ActionButton>Log in</ActionButton>
        <ActionButton>Sign in</ActionButton>
      </ActionContainer>
    </HeaderContainer>
  );
};

export default Header;
