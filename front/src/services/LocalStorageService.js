export const saveUserToken = (token) => {
  window.localStorage.setItem("token", token);
};

export const getUserToken = () => {
  return window.localStorage.getItem("token");
};

export const deleteUserToken = () => {
  window.localStorage.removeItem("token");
};
