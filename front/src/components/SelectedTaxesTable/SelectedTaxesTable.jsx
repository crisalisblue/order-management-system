export const SelectedTaxesTable = ({ selectedTaxes, onRemoveTax }) => {
  return (
    <div className={"mt-4 text-primary"}>
      <h2 className={"text-xl font-semibold mb-2"}>Impuestos Seleccionados</h2>
      <table className={"w-full border-collapse border border-gray-300"}>
        <thead>
          <tr className={"bg-[#85B7CA] text-white border-gray-500"}>
            <th className={"border border-gray-300 p-2"}>Nombre</th>
            <th className={"border border-gray-300 p-2"}>Porcentaje</th>
            <th className={"border border-gray-300 p-2"}>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {selectedTaxes.map((tax, index) => (
            <tr
              className={`${
                index % 2 === 0 ? "bg-[#BCDEEB]" : "bg-[#DBE8EC]"
              } drop-shadow-md p-1`}
              key={index}
            >
              <td className={"border border-gray-300 p-2"}>{tax.name}</td>
              <td className={"border border-gray-300 p-2"}>
                {tax.percentage}%
              </td>
              <td className={"border border-gray-300 p-2"}>
                <button
                  form=""
                  className={"bg-red-500 text-white p-1 rounded"}
                  onClick={() => onRemoveTax(tax.name)}
                >
                  Eliminar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
