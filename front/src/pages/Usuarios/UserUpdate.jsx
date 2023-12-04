import { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import { useParams } from "react-router-dom";
import { updateSingleUser, getSingleUser } from "../../api/UserAPI.js";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

export const UserUpdate = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [user, setUser] = useState(null);
  const [initialValues, setInitialValues] = useState({
    name: "",
    username: "",
    password: "",
  });

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const userData = await getSingleUser(id);
        setUser(userData);
        setInitialValues({
          name: userData.name,
          username: userData.username,
          password: "",
        });
      } catch (error) {
        console.error("Error fetching user data:", error);
      }
    };

    fetchUserData();
  }, [id]);

  const { register, handleSubmit } = useForm();

  const onSubmit = async (data) => {
    data.id = id;
    await updateSingleUser(data);
    showSuccessAlert();
    navigate("/usuarios");
  };

  const showSuccessAlert = () => {
    Swal.fire({
      icon: "success",
      title: "Usuario actualizado",
      showConfirmButton: false,
      timer: 1500,
    });
  };

  if (!user) {
    return <div>Cargando...</div>;
  }

  return (
    <section id="userForm" className="w-5/6 prose min-w-full">
      <form
        className="w-5/6 mx-auto p-6 bg-white rounded-md shadow-md"
        onSubmit={handleSubmit(onSubmit)}
      >
        <div className="mb-4">
          <label
            htmlFor="name"
            className="block text-sm font-semibold text-gray-600"
          >
            Nombre:
          </label>
          <input
            type="text"
            id="name"
            defaultValue={initialValues.name}
            {...register("name")}
            className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>

        <div className="mb-4">
          <label
            htmlFor="username"
            className="block text-sm font-semibold text-gray-600"
          >
            Usuario:
          </label>
          <input
            type="text"
            id="username"
            defaultValue={initialValues.username}
            {...register("username")}
            className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>

        <div className="mb-4">
          <label
            htmlFor="password"
            className="block text-sm font-semibold text-gray-600"
          >
            Contraseña:
          </label>
          <input
            type="password"
            id="password"
            defaultValue={initialValues.password}
            {...register("password")}
            className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>

        <button
          type="submit"
          className="w-1/3 px-4 py-2 text-white bg-primary rounded-md hover:bg-blue-700 focus:outline-none focus:ring focus:border-blue-300"
        >
          Modificar Usuario
        </button>
      </form>
    </section>
  );
};
