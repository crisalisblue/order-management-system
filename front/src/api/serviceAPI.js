import axios from "axios";
const apiUrl = "http://localhost:8080/asset";
export const getAllServices = async () => {
  try {
    const response = await axios.get(`${apiUrl}/readType?type=Service`);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

export const getSingleService = async (serviceID) => {
  try {
    const response = await axios.get(`${apiUrl}/read?id=${serviceID}`);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

export const updateSingleService = async (data) => {
  console.dir(data);
  try {
    const response = await axios.put(`${apiUrl}/update`, data);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

export const deleteSingleService = async (serviceID) => {
  try {
    const response = await axios.delete(`${apiUrl}/delete?id=${serviceID}`);
    return response;
  } catch (error) {
    console.error(error);
  }
};

export const createSingleService = async (data) => {
  try {
    console.dir(data);
    const response = await axios.post(`${apiUrl}/create`, data);
    console.dir(response);
    return response;
  } catch (error) {
    console.error(error);
  }
};
