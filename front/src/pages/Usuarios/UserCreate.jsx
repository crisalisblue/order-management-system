import { createSingleUser } from "../../api/UserAPI";
import { useForm } from "react-hook-form";

export const UserCreate = () => {
  const { register, handleSubmit, reset, control, setValue } = useForm();
  const onSubmit = async (data, e) => {
    console.dir(data);
    console.dir(await createSingleUser(data));
  };
  const onError = (errors, e) => console.log(errors, e);

  return (
    <form
      className={"flex justify-evenly"}
      onSubmit={handleSubmit(onSubmit, onError)}
    >
      userCreate
      <input type="text" {...register("name")} />
      <input type="text" {...register("username")} />
      <input type="password" {...register("password")} />
      <input type="submit" />
    </form>
  );
};
