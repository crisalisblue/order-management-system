import { createSingleUser } from "../../api/UserAPI";
import { CrudTextField } from "../../components/Form/CrudTextField";
import { useForm } from "react-hook-form";

export const UserCreate = () => {
  const { handleSubmit, control } = useForm();
  const onSubmit = async (data, e) => {
    console.dir(await createSingleUser(data));
  };
  const onError = (errors, e) => console.log(errors, e);

  return (
    <form onSubmit={handleSubmit(onSubmit, onError)}>
      <CrudTextField control={control} label="name"></CrudTextField>
      <CrudTextField control={control} label="username"></CrudTextField>
      <CrudTextField control={control} label="password"></CrudTextField>
      <input type="submit" />
    </form>
  );
};
