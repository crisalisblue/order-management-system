import axios from "axios"

export const login = (dataBody) => {
    return axios.post("http://localhost:8080/user/login", dataBody)
        .then((response) => (response.data))
        .catch((err) => Promise.reject(err))
}