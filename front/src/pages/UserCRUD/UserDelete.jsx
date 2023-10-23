import { useForm } from "react-hook-form";
import { deleteSingleUser } from "../../api/UserAPI";
export const UserDelete = (props) => {
  const onError = (errors, e) => console.log(errors, e);
  const { handleSubmit } = useForm();
  const formID = `userForm-${props.userID}`;
  const onSubmit = async (data, e) => {
    console.dir(await deleteSingleUser(props.userID));
  };
  return (
    <form id={formID} onSubmit={handleSubmit(onSubmit, onError)}>
      userDelete
    </form>
  );
};
