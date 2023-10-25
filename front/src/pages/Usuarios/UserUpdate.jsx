import { useForm } from "react-hook-form";
import { useParams } from "react-router-dom";
import { updateSingleUser } from "../../api/UserAPI";
import { useNavigate } from "react-router";
export const UserUpdate = (props) => {
  const navigate = useNavigate();
  const userID = useParams().id;
  const { register, handleSubmit, reset, control, setValue } = useForm({
    defaultValues: {
      id: Number(userID),
    },
  });
  const onError = (errors, e) => console.log(errors, e);
  const onSubmit = async (data, e) => {
    console.log(data, e);
    console.dir(await updateSingleUser(data));
    navigate("/usuarios");
  };

  return (
    <form
      className={"flex justify-evenly"}
      onSubmit={handleSubmit(onSubmit, onError)}
    >
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
