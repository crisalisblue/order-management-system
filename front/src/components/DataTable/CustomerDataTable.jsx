import { CustomerModal } from "../DeleteModal/CustomerModal";
export const CustomerDataTable = ({ data }) => {
  return (
    
    <table className={"w-5/6 m-auto text-black"}>
      <thead >
        <tr className={"bg-[#85B7CA]"}>
          <th className={"text-center rounded-tl-md p-1"}>Nombre</th>
          <th className={"text-center p-1"}>Documento</th>
          <th className={"text-center p-1"}>Tipo</th>
          <th className={"text-center rounded-tr-md p-1"}>Acciones</th>
        </tr>
      </thead>
      <tbody>
        {
        
        data.length === 0 ? 
          (
            <>
            <tr className={"bg-[#DBE8EC]"}>
              <td className={"text-center"}>-</td>
              <td className={"text-center"}>-</td>
              <td className={"text-center"}>-</td>
              <td className={"text-center"}>-</td>
            </tr>
            <tr className={"bg-[#BCDEEB]"}>
              <td className={"text-center rounded-bl-md"}>-</td>
              <td className={"text-center"}>-</td>
              <td className={"text-center"}>-</td>
              <td className={"text-center rounded-br-md"}>-</td>
            </tr>
            </>
          )
          :
        data.map((item, index) => (
          
          <tr 
           className={`${(index % 2 === 0)? "bg-[#BCDEEB]" : "bg-[#DBE8EC]"} drop-shadow-md p-1`}
          key={index}>

            
            <td className={`text-center ${(index === data.length - 1) ? "rounded-bl-md" : ""} p-1`}>{item.type == "Empresa" ? item.businessName : item.lastName+", "+ item.name}</td>
            <td className={"text-center p-1"}>{item.type == "Empresa" ?item.cuit : item.dni }</td>
            <td className={"text-center p-1"}>{item.type}</td>
            <td className={`text-center ${(index === data.length - 1) ? "rounded-br-md" : ""} p-1`}>
                <a href={`/clientes/${item.id}/editar`}>
                  <button className={"hover:text-[#008A70] hover:scale-125 duration-300 bolder mx-2"}>✎</button>
                </a>
                <CustomerModal itemId={item.id}>
                </CustomerModal>
              </td>
          </tr>
        ))}
       
       
      </tbody>
    </table>
  );
};