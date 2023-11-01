import { createSingleUser } from "../../api/UserAPI";
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
      <form onSubmit={handleSubmit(onSubmit)}>
        <table>
          <tbody>
            <tr className="text-accent odd:bg-secondary even:bg-base-100">
              <td>
                <input type="text" {...register("name")} />
              </td>
              <td>
                <input type="text" {...register("username")} />
              </td>
              <td>
                <input type="password" {...register("password")} />
              </td>
              <td>
                <button className="btn btn-success" type="submit">
                  Agregar
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </form>
    </section>
  );
};
