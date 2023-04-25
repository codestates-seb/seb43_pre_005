import styled from "styled-components";
import Layout from "../../components/common/Layout";
import SearchBox from "./UserSearchbox.js";

const UserDesign = styled.div`
  padding: 24px;
  .usersearch {
    padding: 2px 2px 2px 2px;
    margin: 0 0 4px 6px;
    text-align: left;
    font-size: 20px;
    line-height: 20px;
  }
`;

const Users = () => {
  return (
    <Layout>
      <UserDesign>
        <h1>Users</h1>
        <SearchBox />
      </UserDesign>
    </Layout>
  );
};

export default Users;
