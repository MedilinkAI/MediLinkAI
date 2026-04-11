import axios from 'axios';

// Vite requires environment variables to start with "VITE_" to be exposed to the client bundle.
const API_URL = import.meta.env.VITE_API_BASE_URL;

const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request Interceptor
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Response Interceptor
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.data) {
      const data = error.response.data;
      let backendMessage = null;

      if (typeof data === 'string') {
        if (!data.trim().startsWith('<html')) {
          backendMessage = data;
        }
      } else if (typeof data === 'object') {
        backendMessage = data.message || data.error || data.detail;
      }

      if (backendMessage) {
        error.message = backendMessage;
      }
    }

    if (error.response && error.response.status === 401) {
      // Global handling for unauthorized access (e.g. token expired)
      const isAuthPage = window.location.pathname.includes('/login') || window.location.pathname.includes('/register');
      if (!isAuthPage) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        window.location.href = '/login';
      }
    }
    return Promise.reject(error);
  }
);

export default api;
