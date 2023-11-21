import axios from "axios";
const apiUrl = "http://localhost:8080/order";
export const getAllOrders = async () => {
  try {
    const response = await axios.get(`${apiUrl}/list`);
    console.dir(response);
    console.dir(response.status);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

export const createSingleOrder = async (data) => {
  try {
    console.dir(data);
    const response = await axios.post(`${apiUrl}/create`, data);
    console.dir(response);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};

export const refreshOrder = async (data) => {
  try {
    console.dir(data);
    const response = await axios.post(`${apiUrl}/refresh`, data);
    console.dir("La respuesta a refresh es: ", response);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

export const deleteSingleOrder = async (orderId) => {
  try {
    const response = await axios.delete(`${apiUrl}/delete?id=${orderId}`);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};
