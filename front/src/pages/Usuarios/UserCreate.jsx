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
    <section id="userForm" className="bg-base-200 prose min-w-full">
      <form
        className="gap-4 p-4 flex flex-col"
        onSubmit={handleSubmit(onSubmit)}
      >
        <div>
          <label htmlFor="name">Nombre:</label>
          <input type="text" {...register("name")} />
        </div>

        <div>
          <label htmlFor="username">Usuario:</label>
          <input type="text" {...register("username")} />
        </div>

        <div>
          <label htmlFor="password">Contraseña:</label>
          <input type="password" {...register("password")} />
        </div>

        <button className="btn bg-primary text-base-100" type="submit">
          Agregar
        </button>
      </form>
      <article>
        <h1>Roles</h1>
        <table>
          <thead>
            <th>Rol</th>
            <th>Seleccionado</th>
          </thead>
          <tbody>
            <tr>
              <td>Administrador</td>
              <input type="checkbox" name="" id="" />
            </tr>
            <tr>
              <td>Usuario</td>
              <input type="checkbox" name="" id="" />
            </tr>
          </tbody>
        </table>
      </article>
    </section>
  );
};
