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
    let mockData={
      "idOrder": 0,
      "dateOrder": "2023-11-17T11:23:59.109Z",
      "totalDiscount": 0,
      "totalPrice": 0,
      "subTotal": 0,
      "active": true,
      "customerID": 0,
      "customerName": "string",
      "itemDTO": [
        {
          "id": 0,
          "idAsset": 0,
          "nameAsset": "string",
          "orderDTO": "string",
          "itemPrice": 0,
          "itemDitails": "string",
          "itemQuantity": 0,
          "discountAmount": 0,
          "totalPrice": 0,
          "warrantyYears": 0
        }
      ],
      "calculatedTaxDTOS": [
        {
          "calculatedTaxID": 0,
          "taxID": 0,
          "taxName": "string",
          "taxesAmount": 0
        }
      ]
    }
    console.dir(data);
    const response = await axios.post(`${apiUrl}/create`, data);
    console.dir(mockData)
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

export const deleteOrder = async (orderId) => {
  try {
    const response = await axios.delete(`${apiUrl}/delete?id=${orderId}`);
    return response.status;
  } catch (error) {
    console.error(error);
  }
};
