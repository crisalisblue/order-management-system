import axios from "axios";
const apiUrl = "http://localhost:8080/subscription";
export const getAllSubscriptions = async () => {
  try {
    const response = await axios.get(`${apiUrl}/list`);
    console.dir(response.data);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};
export const getSingleSubscription = async (subscriptionId) => {
  try {
    const response = await axios.get(`${apiUrl}/read?id=${subscriptionId}`);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

export const updateSingleSubscription = async (data) => {
  console.dir(data);
  try {
    const response = await axios.put(`${apiUrl}/update`, data);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};

export const deleteSingleSubscription = async (userId) => {
  try {
    const response = await axios.delete(`${apiUrl}/delete?id=${userId}`);
    console.dir(`${apiUrl}/${userId}`);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};

export const createSingleSubscription = async (data) => {
  try {
    const response = await axios.post(`${apiUrl}/create`, data);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};
