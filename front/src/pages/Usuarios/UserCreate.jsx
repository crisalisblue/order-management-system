import { createSingleUser } from "../../api/UserAPI";
import { useForm } from "react-hook-form";

export const UserCreate = () => {
  const { handleSubmit, control } = useForm();
  const onSubmit = async (data, e) => {
    console.dir(await createSingleUser(data));
  };
  const onError = (errors, e) => console.log(errors, e);

  return (
    <form onSubmit={handleSubmit(onSubmit, onError)}>
      userCreate
      <input type="text" />
      <input type="text" />
      <input type="password" name="" id="" />
      <input type="submit" />
    </form>
  );
};
