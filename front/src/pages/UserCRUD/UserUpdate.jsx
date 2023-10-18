import { useForm } from "react-hook-form";
import { useParams } from "react-router-dom";
import { updateSingleUser } from "../../api/UserAPI";
import { CrudTextField } from "../../components/Form/CrudTextField";

export const UserUpdate = (props) => {
  const userID = useParams().id;
  const { register, handleSubmit, reset, control, setValue } = useForm({
    defaultValues: {
      id: userID,
    },
  });
  const onError = (errors, e) => console.log(errors, e);
  const onSubmit = async (data, e) => {
    console.log(data, e);
    console.dir(await updateSingleUser(userID, data));
  };

  return (
    <form onSubmit={handleSubmit(onSubmit, onError)}>
      <CrudTextField
        control={control}
        label="id"
        isDisabled={true}
        defaultValue={userID}
      ></CrudTextField>
      <CrudTextField control={control} label="user"></CrudTextField>
      <CrudTextField control={control} label="username"></CrudTextField>
      <CrudTextField control={control} label="password"></CrudTextField>
      <input type="submit" />
    </form>
  );
};
