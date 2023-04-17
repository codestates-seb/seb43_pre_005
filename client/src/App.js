import { BrowserRouter, Routes, Route } from "react-router-dom";
import Footer from "./components/Footer";
import "./App.css";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Footer />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
