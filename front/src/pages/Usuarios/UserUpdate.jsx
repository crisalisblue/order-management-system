import { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import { useParams } from "react-router-dom";
import { updateSingleUser, getSingleUser } from "../../api/UserAPI";
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
    data.id = id; // Agrega el ID al objeto de datos antes de enviarlo
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
    <section id="userForm" className="bg-base-200 prose min-w-full">
      <form
        className="gap-4 p-4 flex flex-col"
        onSubmit={handleSubmit(onSubmit)}
      >
        <div>
          <label htmlFor="name">Nombre:</label>
          <input
            defaultValue={initialValues.name}
            type="text"
            {...register("name")}
          />
        </div>
        <div>
          <label htmlFor="username">Usuario:</label>
          <input
            defaultValue={initialValues.username}
            type="text"
            {...register("username")}
          />
        </div>
        <div>
          <label htmlFor="password">Contraseña:</label>
          <input
            defaultValue={initialValues.password}
            type="password"
            {...register("password")}
          />
        </div>
        <input type="hidden" defaultValue={id} {...register("id")} />
        <button className="btn bg-primary text-base-100" type="submit">
          Modificar
        </button>
      </form>
      <article>
        <h1>Rol</h1>
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
