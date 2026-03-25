import api from './api';

const authService = {
  login: async (emailOrMobile, password) => {
    const response = await api.post('users/login', { 
      identifier: emailOrMobile, 
      password 
    });
    return response.data;
  },
  
  register: async (userData) => {
    const payload = {
      ...userData,
      phone: userData.phoneNumber
    };
    const response = await api.post('users/register', payload);
    return response.data;
  },

  verifyOtp: async (identifier, otp) => {
    const response = await api.get(`users/verifyOtp/${encodeURIComponent(identifier)}/${encodeURIComponent(otp)}`);
    return response.data;
  },

  verifyMfaSecret: async (identifier, secret) => {
    const response = await api.get(`users/verifyMfaSecret/${encodeURIComponent(identifier)}/${encodeURIComponent(secret)}`);
    return response.data;
  }
};

export default authService;
