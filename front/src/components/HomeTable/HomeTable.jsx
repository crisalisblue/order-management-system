export const HomeTable = ({ col1, col2, col3, col4, numRows }) => {
    const data = Array(numRows).fill({});

    return (
        <table className={"w-5/6 m-auto text-black"}>
            <thead>
            <tr className={"bg-[#85B7CA]"}>
                <th className={"text-center rounded-tl-md p-1"}>{col1}</th>
                <th className={"text-center p-1"}>{col2}</th>
                <th className={"text-center p-1"}>{col3}</th>
                <th className={"text-center rounded-tr-md p-1"}>{col4}</th>
            </tr>
            </thead>
            <tbody>
            {data.map((row, index) => (
                <tr key={index} className={`${(index % 2 === 0)? "bg-[#BCDEEB]" : "bg-[#DBE8EC]"} drop-shadow-md p-1`}>
                    <td className={`text-center ${(index === data.length - 1) ? "rounded-bl-md" : ""} p-1`}>{index}</td>
                    <td className={"text-center p-1"}>{col2}</td>
                    <td className={"text-center p-1"}>{col3}</td>
                    <td className={`text-center ${(index === data.length - 1) ? "rounded-br-md" : ""} p-1 flex justify-evenly`}>
                        <button className={"hover:text-[#008A70] hover:scale-125 duration-300 bolder"}>✎</button>
                        <button className={"hover:text-red-700 hover:scale-125 duration-300 bolder"}>🗑</button>
                    </td>
                </tr>
            ))}
            </tbody>
        </table>
    );
};
