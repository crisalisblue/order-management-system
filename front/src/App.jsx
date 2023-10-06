import { ThemeProvider } from '@emotion/react'
import { CssBaseline, createTheme } from '@mui/material'
import './App.css'
import Login from './pages/Login'

function App() {

  const theme = createTheme()

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline/>
        <Login/>
    </ThemeProvider>
  )
}

export default App
