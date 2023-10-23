import { useForm } from "react-hook-form";
import { useParams } from "react-router-dom";
import { getSingleUser, updateSingleUser } from "../../api/UserAPI";
import { CrudTextField } from "../../components/Form/CrudTextField";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";

export const UserUpdate = () => {
  const userID = useParams().id;
  const [userData, setUserData] = useState({
    name: "",
    username: "",
    password: "",
  });

  const { register, handleSubmit, control, setValue } = useForm();

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const { data } = await getSingleUser(userID);
        setUserData(data);
        setValue("id", userID);
        setValue("name", data.name);
        setValue("username", data.username);
        setValue("password", data.password);
      } catch (error) {
        console.error(error);
      }
    };

    fetchUserData();
  }, [userID]);

  const onSubmit = async (formData, e) => {
    console.log(formData, e);
    try {
      await updateSingleUser(formData);
      toast("Updated");
    } catch (error) {
      console.error(error);
      toast("error");
    }
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <CrudTextField
        control={control}
        label="name"
        defaultValue={userData.name}
      ></CrudTextField>
      <CrudTextField
        control={control}
        label="username"
        defaultValue={userData.username}
      ></CrudTextField>
      <CrudTextField
        control={control}
        label="password"
        defaultValue={userData.password}
      ></CrudTextField>
      <input type="submit" value="Editar" />
    </form>
  );
};
