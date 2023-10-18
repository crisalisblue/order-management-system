import axios from "axios"

export const login = (dataBody) => {
    return axios.post("http://localhost:8080/user/login", dataBody)
        .then((response) => ({"user": response.data, "token": response.headers['auth']}))
        .catch((err) => Promise.reject(err))
}