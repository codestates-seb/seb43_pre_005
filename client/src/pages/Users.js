import styled from "styled-components";
import Layout from "../components/common/Layout";
import SearchBox from "./UserSearchbox.js";
import { dummydata } from "../data/userdummydata.js";
import { useState } from "react";
// import axios from "axios";

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
  /*   const [data, setData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const result = await axios(
        'https://localhost:3000',
      );
      setData(result.data);
    };
    fetchData();
  }, []); */

  return (
    <Layout>
      <UserDesign>
        <h1>Users</h1>
        <SearchBox data={dummydata} />
      </UserDesign>
    </Layout>
  );
};

export default Users;
