
export const ProductosTableComponent = ({ data }) => {
    return (
        <table className="table table-zebra table-xs bg-primary text-primary">
            <thead>
            <tr className="bg-secondary">
                <th>Name</th>
                <th>MountBase</th>
                <th>Warranty</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            {data.map((item , index) => (
                <tr key={index}>
                    <td>{item.name}</td>
                    <td>{item.mountBase}</td>
                    <td>{item.warranty}</td>
                    <td className="flex justify-evenly">
                        <a href={`/productos/${item.id}/editar`}>
                            <button className="btn btn-success">Editar</button>
                        </a>
                    </td>
                </tr>
            ))}
            </tbody>
        </table>
    );
};
