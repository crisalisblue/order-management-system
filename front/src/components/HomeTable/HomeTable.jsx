export const HomeTable = ({ col1, col2, col3, col4, numRows }) => {
    const data = Array(numRows).fill({});

    return (
        <table className={"w-5/6 m-auto text-black"}>
            <thead>
            <tr className={"bg-[#85B7CA]"}>
                <th className={"text-center rounded-tl-md"}>{col1}</th>
                <th className={"text-center"}>{col2}</th>
                <th className={"text-center"}>{col3}</th>
                <th className={"text-center rounded-tr-md"}>{col4}</th>
            </tr>
            </thead>
            <tbody>
            {data.map((row, index) => (
                <tr key={index} className={`${(index % 2 === 0)? "bg-[#BCDEEB]" : "bg-[#DBE8EC]"} drop-shadow-md`}>
                    <td className={`text-center ${(index === data.length - 1) ? "rounded-bl-md" : ""}`}>{index}</td>
                    <td className={"text-center"}>{col2}</td>
                    <td className={"text-center"}>{col3}</td>
                    <td className={`text-center ${(index === data.length - 1) ? "rounded-br-md" : ""}`}>
                        <button>✎</button>
                        <button>🗑</button>
                    </td>
                </tr>
            ))}
            </tbody>
        </table>
    );
};
