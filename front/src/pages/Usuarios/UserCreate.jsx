import { createSingleUser } from "../../api/UserAPI";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";

export const UserCreate = () => {
  const navigate = useNavigate();
  const { register, handleSubmit } = useForm();

  const onSubmit = async (data) => {
    console.dir(data);
    console.dir(await createSingleUser(data));
    navigate(0);
  };

  return (
    <>
      <tr className="text-accent odd:bg-secondary even:bg-base-100">
        <td className="w-1/4">
          <input type="text" {...register("name")} />
        </td>
        <td className="w-1/4">
          <input type="text" {...register("username")} />
        </td>
        <td className="w-1/4">
          <input type="password" {...register("password")} />
        </td>
        <td className="flex">
          <button
            className="btn btn-success"
            type="button"
            onClick={handleSubmit(onSubmit)}
          >
            Agregar
          </button>
        </td>
      </tr>
    </>
  );
};
