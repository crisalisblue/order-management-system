import axios from "axios";
const apiUrl = "http://localhost:8080/item";
export const getAllOrders = async () => {
  try {
    const response = await axios.get(`${apiUrl}/read`);
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