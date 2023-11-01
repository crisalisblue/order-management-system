import { createSingleUser } from "../../api/UserAPI";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
export const UserCreate = () => {
  const navigate = useNavigate();
  const { register, handleSubmit, reset, control, setValue } = useForm();
  const onSubmit = async (data, e) => {
    console.dir(data);
    console.dir(await createSingleUser(data));
    navigate("/usuarios");
  };
  const onError = (errors, e) => console.log(errors, e);

  return (
    <form
      className={"flex justify-evenly items-center"}
      onSubmit={handleSubmit(onSubmit, onError)}
    >
      <label>name</label>
      <input type="text" {...register("name")} />
      <label>username</label>
      <input type="text" {...register("username")} />
      <label>password</label>
      <input type="password" {...register("password")} />
      <input className="btn" type="submit" />
    </form>
  );
};
