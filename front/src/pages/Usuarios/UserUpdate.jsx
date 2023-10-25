import { useForm } from "react-hook-form";
import { useParams } from "react-router-dom";
import { updateSingleUser } from "../../api/UserAPI";

export const UserUpdate = (props) => {
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
  };

  return (
    <form
      className={"flex justify-evenly"}
      onSubmit={handleSubmit(onSubmit, onError)}
    >
      {userID}
      <input type="text" {...register("name")} />
      <input type="text" {...register("username")} />
      <input type="password" {...register("password")} />
      <input type="submit" />
    </form>
  );
};
