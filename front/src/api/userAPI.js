import axios from "axios";
const apiUrl = "http://localhost:8080/user";
export const getAllUsers = async () => {
  try {
    const response = await axios.get(`${apiUrl}/List`);
    console.dir(response);
    console.dir(response.status);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

export const getSingleUser = async (userId) => {
  try {
    const response = await axios.get(
      `https://my-json-server.typicode.com/lucascn21/demo/users?id=${userId}`
    );
    return response.status;
  } catch (error) {
    console.error(error);
  }
};

export const updateSingleUser = async (data) => {
  console.dir(data);
  try {
    const response = await axios.put(`${apiUrl}/Update`, data);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};

export const deleteSingleUser = async (userId) => {
  try {
    const response = await axios.delete(`${apiUrl}/Delete/${userId}`);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};

export const createSingleUser = async (data) => {
  try {
    console.dir(data);
    const response = await axios.post(`${apiUrl}/Created`, data);
    console.dir(response);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};
