import { useForm } from "react-hook-form";
import { useParams } from "react-router-dom";
import { updateSingleProduct } from "../../api/productAPI";
import { useNavigate } from "react-router";
export const ProductsUpdate = () => {
  const navigate = useNavigate();
  const userID = useParams().id;
  const { register, handleSubmit } = useForm({
    defaultValues: {
      id: Number(userID),
    },
  });
  const onError = (errors, e) => console.log(errors, e);
  const onSubmit = async (data, e) => {
    console.log(data, e);
    console.dir(await updateSingleProduct(data));
    navigate("/productos");
  };

  return (
    <form className={""} onSubmit={handleSubmit(onSubmit, onError)}>
      <label>name</label>
      <input type="" {...register("name")} />
      <label>mountBase</label>
      <input type="" {...register("mountBase")} />
      <label>warranty</label>
      <input type="text" {...register("warranty")} />
      <input className="" type="submit" />
    </form>
  );
};
