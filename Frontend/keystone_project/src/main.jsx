import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'

import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { ToastContainer  } from 'react-toastify';

import './styles/auth.css';
import './styles/signup.css';

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
    <ToastContainer position="top-right" />
  </StrictMode>,
)
