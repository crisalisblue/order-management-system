import axios from "axios";
const apiUrl = "http://localhost:8080/asset";
export const getAllProducts = async () => {
  try {
    const response = await axios.get(`${apiUrl}/list`);
    console.dir(response);
    console.dir(response.status);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};
//http://localhost:8080/user?id=3
export const getSingleProduct = async (productID) => {
  try {
    const response = await axios.get(`${apiUrl}?id=${productID}`);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};

export const updateSingleProduct = async (data) => {
  console.dir(data);
  try {
    const response = await axios.put(`${apiUrl}/update`, data);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};

export const deleteSingleProduct = async (productID) => {
  try {
    const response = await axios.delete(`${apiUrl}delete?id=${productID}`);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};

export const createSingleProduct = async (data) => {
  try {
    console.dir(data);
    const response = await axios.post(`${apiUrl}/create`, data);
    console.dir(response);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};
