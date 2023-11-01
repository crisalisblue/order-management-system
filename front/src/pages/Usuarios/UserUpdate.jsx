import { useForm } from "react-hook-form";
import { useParams } from "react-router-dom";
import { updateSingleUser } from "../../api/UserAPI";
import { useNavigate } from "react-router";
import Swal from "sweetalert2";

export const UserUpdate = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const { register, handleSubmit } = useForm({
    defaultValues: {
      id: Number(id),
    },
  });

  const onSubmit = async (data) => {
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

  return (
    <form className="flex justify-evenly" onSubmit={handleSubmit(onSubmit)}>
      <label>name</label>
      <input type="text" {...register("name")} />
      <label>username</label>
      <input type="text" {...register("username")} />
      <label>password</label>
      <input type="password" {...register("password")} />
      <input type="submit" />
    </form>
  );
};
