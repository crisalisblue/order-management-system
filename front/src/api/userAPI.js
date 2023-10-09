import axios from "axios";

export const getAllUsers = async () => {
  const apiUrl = "https://my-json-server.typicode.com/lucascn21/demo/users";
  try {
    const response = await axios.get(apiUrl);
    console.dir(response.status);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const getSingleUser = async (userId) => {
  try {
    const response = await axios.get(
      `https://my-json-server.typicode.com/lucascn21/demo/users?id=${userId}`
    );
    return response.status;
  } catch (error) {
    throw error;
  }
};

export const updateSingleUser = async (userId, data) => {
  try {
    const response = await axios.put(
      `https://my-json-server.typicode.com/lucascn21/demo/users/${userId}`,
      data
    );

    return response.status;
  } catch (error) {
    throw error;
  }
};

export const deleteSingleUser = async (userId) => {
  try {
    const response = await axios.delete(
      `https://my-json-server.typicode.com/lucascn21/demo/users/${userId}`
    );
    return response.status;
  } catch (error) {
    throw error;
  }
};
