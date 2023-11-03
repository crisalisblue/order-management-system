import { createSingleProduct } from "../../api/productAPI.js";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
export const ProdcuctCreate = () => {
    const navigate = useNavigate();
    const { register, handleSubmit, reset, control, setValue } = useForm();
    const onSubmit = async (data, e) => {
        console.dir(data);
        console.dir(await createSingleProduct(data));
        navigate("/productos");
    };
    const onError = (errors, e) => console.log(errors, e);

    return (
        <form
            className={""}
            onSubmit={handleSubmit(onSubmit, onError)}
        >
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
