import styled from "styled-components";
import Footer from "./Footer";
import Sidebar from "./Sidebar";
import Header from "./Header";

const MainContent = styled.div`
  display: flex;
`;

const Layout = (props) => {
  const { children } = props;
  return (
    <>
      <Header />
      <MainContent>
        <Sidebar />
        {children}
      </MainContent>
      <Footer />
    </>
  );
};

export default Layout;
