import { createSingleUser } from "../../api/UserAPI.js";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

export const UserCreate = () => {
  const navigate = useNavigate();
  const { register, handleSubmit } = useForm();

  const onSubmit = async (data) => {
    try {
      await createSingleUser(data);
      Swal.fire({
        icon: "success",
        title: "Usuario creado",
        text: "El usuario se creó exitosamente.",
      }).then((result) => {
        if (result.isConfirmed) {
          navigate("/usuarios");
        }
      });
    } catch (error) {
      Swal.fire({
        icon: "error",
        title: "Error al crear usuario",
        text: "Hubo un problema al crear el usuario.",
      });
    }
  };

  return (
    <section id="userForm" className="w-5/6  prose min-w-full">
      <form
        className="w-5/6  mx-auto p-6 bg-white rounded-md shadow-md"
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
            {...register("password")}
            className="w-full px-4 py-2 mt-2 border rounded-md focus:outline-none focus:ring focus:border-blue-300"
          />
        </div>

        <button
          type="submit"
          className="w-1/3 px-4 py-2 text-white bg-primary rounded-md hover:bg-blue-700 focus:outline-none focus:ring focus:border-blue-300"
        >
          Agregar Usuario
        </button>
      </form>
    </section>
  );
};
