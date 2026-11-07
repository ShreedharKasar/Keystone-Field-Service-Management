import api from './api';

// Customer APIs
export const getAllCustomers = async () => {
const response = await api.get('/api/customers/getAll');
return response.data;
};

// Technician APIs
export const getAllTechnicians = async () => {
const response = await api.get('/api/technician/getAll');
return response.data;
};
