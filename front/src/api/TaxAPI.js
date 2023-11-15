import axios from "axios";
const apiUrl = "http://localhost:8080/tax";
export const getAllTaxes = async () => {
  try {
    const response = await axios.get(`${apiUrl}/read`);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};
//http://localhost:8080/tax?id=3
export const getSingleTax = async (taxId) => {
  try {
    const response = await axios.get(`${apiUrl}?id=${taxId}`);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

export const updateSingleTax = async (data) => {
  try {
    const response = await axios.put(`${apiUrl}/update`, data);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};

export const deleteSingleTax = async (taxId) => {
  try {
    const response = await axios.delete(`${apiUrl}?id=${taxId}`);
    console.dir(`${apiUrl}/${taxId}`);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};

export const createSingleTax = async (data) => {
  try {
    const response = await axios.post(`${apiUrl}/create`, data);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};

// export const login = (dataBody) => {
//   return axios
//     .post("http://localhost:8080/user/login", dataBody)
//     .then((response) => response.data)
//     .catch((err) => Promise.reject(err));
// };
