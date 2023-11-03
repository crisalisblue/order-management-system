import { createSingleProduct } from "../../api/productAPI.js";
import { useForm } from "react-hook-form";
import { useNavigate } from "react-router";
import {HomeTable} from "../../components/HomeTable/HomeTable.jsx";
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
            className={"bg-[#F1F1F1] flex justify-evenly flex-wrap p-4 rounded-md drop-shadow-md w-5/6 mx-auto my-4"}
            onSubmit={handleSubmit(onSubmit, onError)}
        >
            <div className={"flex flex-col w-1/2"}>
                <label className={"text-black text-xl my-5 flex justify-center items-center"}>Nombre
                    <input className="bg-white rounded-md drop-shadow-md text-black w-1/3 mx-4" type="text" {...register("name")} />
                </label>
                <label className={"text-black text-xl my-5 flex justify-center items-center"}>Precio Unitario
                    <input className="bg-white rounded-md drop-shadow-md text-black w-1/3 mx-4" type="number" {...register("mountBase")} />
                </label>
                <label className={"text-black text-xl my-5 flex justify-center items-center"}>Garantía
                    <input className="bg-white rounded-md drop-shadow-md text-black w-1/3 mx-4" type="text" {...register("warranty")} />
                </label>
                <label className={"text-black text-xl my-5 flex justify-center items-center"}>Impuesto
                    <select className="bg-white rounded-md drop-shadow-md text-black w-1/3 mx-4">
                        <option value="1">IVA</option>
                        <option value="2">IVA</option>
                        <option value="3">IVA</option>
                    </select></label>
            </div>
            <div className={"w-1/2"}>
                <HomeTable col1={"ID"} col2={"Impuesto"} col3={"Porcentaje"} col4={""} numRows={5}/>
            </div>
            <input className={"bg-[#001F3D] rounded-md text-white p-2 w-fit mx-auto my-2 cursor-pointer"} type="submit" value="Agregar Producto"/>
        </form>
    );
};
