import axios from "axios";
const apiUrl = "http://localhost:8080/asset";
export const getAllAssets = async () => {
  try {
    const response = await axios.get(`${apiUrl}/read`);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};