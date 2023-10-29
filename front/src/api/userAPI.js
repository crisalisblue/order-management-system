import axios from "axios";
const apiUrl = "http://localhost:8080/user";
export const getAllUsers = async () => {
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
export const getSingleUser = async (userId) => {
  try {
    const response = await axios.get(`${apiUrl}?id=${userId}`);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};

export const updateSingleUser = async (data) => {
  console.dir(data);
  try {
    const response = await axios.put(`${apiUrl}/update`, data);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};

export const deleteSingleUser = async (userId) => {
  try {
    const response = await axios.delete(`${apiUrl}/${userId}`);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};

export const createSingleUser = async (data) => {
  try {
    console.dir(data);
    const response = await axios.post(`${apiUrl}/create`, data);
    console.dir(response);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};

export const login = (dataBody) => {
  return axios
    .post("http://localhost:8080/user/login", dataBody)
    .then((response) => response.data)
    .catch((err) => Promise.reject(err));
};
