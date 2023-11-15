import axios from "axios";
const apiUrl = "http://localhost:8080/customer";
export const getAllCustomers = async () => {
  try {
    const response = await axios.get(`${apiUrl}/list`);
    console.dir(response);
    console.dir(response.status);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

export const getSingleCustomer = async (customerId) => {
  try {
    const response = await axios.get(`${apiUrl}/read?id=${customerId}`);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

export const updateSingleCustomer = async (data) => {
  console.dir(data);
  try {
    const response = await axios.put(`${apiUrl}/update`, data);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};

export const deleteSingleCustomer = async (customerId) => {
  try {
    const response = await axios.delete(`${apiUrl}/delete?id=${customerId}`);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};

export const createSingleCustomer = async (data) => {
  try {
    console.dir(data);
    const response = await axios.post(`${apiUrl}/create`, data);
    console.dir(response);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};
