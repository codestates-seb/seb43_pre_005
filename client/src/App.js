import { BrowserRouter, Routes, Route } from "react-router-dom";
import Footer from "./components/Footer";
import "./App.css";
import Sidebar from "./components/Sidebar";

function App() {
  return (
    <BrowserRouter>
      <Sidebar />
      <Routes>
        <Route path="/" element={<Footer />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
