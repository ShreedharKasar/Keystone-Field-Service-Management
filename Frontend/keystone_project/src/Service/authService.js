import api from './api';

// Login API
export const loginUser = async (userData) => {
    const response = await api.post(
        '/api/auth/login',
        userData
    );

    console.log('Login Response:', response.data);
    return response.data;
};

// Register API
export const registerUser = async (userData) => {
    const response = await api.post(
        '/api/auth/register',
        userData
    );

    return response.data;
};

// Forgot Password API
export const forgotPassword = async (userEmail) => {
    const response = await api.post(
        '/api/auth/forgot-password',
        { userEmail }
    );

    return response.data;
};

// Reset Password API
export const resetPassword = async (token, resetData) => {
    const response = await api.post(
        `/api/auth/reset-password?token=${encodeURIComponent(token)}`,
        resetData
    );

    return response.data;
};