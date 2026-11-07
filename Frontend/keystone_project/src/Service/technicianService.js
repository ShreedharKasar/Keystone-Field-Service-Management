import api from "./api";

export const getAllTechnicians = async() =>{
    const response = await api.get('/api/technician/getAll')
    return response.data;
}