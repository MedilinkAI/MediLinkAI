import { createSlice } from '@reduxjs/toolkit';

const getSavedItem = (key) => {
  const item = localStorage.getItem(key);
  return (item && item !== 'null' && item !== 'undefined') ? item : null;
};

const savedUser = getSavedItem('user');
const savedToken = getSavedItem('token');

const initialState = {
  user: savedUser ? JSON.parse(savedUser) : null,
  token: savedToken,
  isAuthenticated: !!savedToken,
  loading: false,
  error: null,
};

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    loginStart: (state) => {
      state.loading = true;
      state.error = null;
    },
    loginSuccess: (state, action) => {
      state.loading = false;
      state.isAuthenticated = true;
      state.user = action.payload.user;
      state.token = action.payload.token;
      localStorage.setItem('user', JSON.stringify(action.payload.user));
      localStorage.setItem('token', action.payload.token);
    },
    loginFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },
    loginStopLoading: (state) => {
      state.loading = false;
    },
    logout: (state) => {
      state.user = null;
      state.token = null;
      state.isAuthenticated = false;
      localStorage.removeItem('user');
      localStorage.removeItem('token');
    },
    clearError: (state) => {
      state.error = null;
    }
  },
});

export const { loginStart, loginSuccess, loginFailure, loginStopLoading, logout, clearError } = authSlice.actions;

export default authSlice.reducer;
