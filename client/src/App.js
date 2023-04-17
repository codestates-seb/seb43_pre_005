import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./pages/Home";
import Tags from "./pages/Tags";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/tags" element={<Tags />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
