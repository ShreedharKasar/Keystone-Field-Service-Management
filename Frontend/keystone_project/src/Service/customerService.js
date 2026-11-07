import api from './api';

export const getAllCustomers = async () => {
    const response = await api.get('/api/customers/getAll');
    return response.data;
};