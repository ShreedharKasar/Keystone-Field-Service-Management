import api from './api';

// Get all work orders
export const getAllWorkOrders = async () => {
    const response = await api.get('/api/workOrder/getAll');
    return response.data;
};

// Get work order by ID
export const getWorkOrderById = async (id) => {
    const response = await api.get(`/api/workOrder/get/${id}`);
    return response.data;
};