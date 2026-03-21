import { useState } from "react";
import "./App.css";
import { createTheme, MantineProvider } from "@mantine/core";
import HomePage from "./Pages/HomePage";
import "@mantine/core/styles.css";
import "./App.css";
import { BrowserRouter, Routes,Route } from "react-router-dom";

function App() {
  const [count, setCount] = useState(0);
  const theme = createTheme({
    colors: {
      "bright-sun": [
        "#fffbea",
        "#fff3c4",
        "#fce588",
        "#fadb5f",
        "#f7c948",
        "#f0b429",
        "#de911d",
        "#cb6e17",
        "#b44d12",
        "#8d2b0b",
      ],
      "mine-shaft": [
        "#f9f9f9",
        "#e9e9e9",
        "#d0d0d0",
        "#b8b8b8",
        "#a0a0a0",
        "#858585",
        "#6b6b6b",
        "#515151",
        "#3b3b3b",
        "#262626",
      ],
    },
  });
  return (
    <MantineProvider defaultColorScheme="dark" theme={theme}>
      <BrowserRouter>
      <Routes>
        <Route path="*" element = {<HomePage/>}/>
      </Routes>
      </BrowserRouter>
    </MantineProvider>
  );
}

export default App;
