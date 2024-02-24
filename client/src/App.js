import logo from "./logo.svg";
import "./App.css";
import { CssBaseline, ThemeProvider } from "@mui/material";
import theme from "./theme/theme";
import HomePage from "./customer/pages/HomePage";
import Routers from "./routes/Routers";

function App() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Routers />
    </ThemeProvider>
  );
}

export default App;
