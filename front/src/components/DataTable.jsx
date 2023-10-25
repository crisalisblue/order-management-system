export const DataTable = ({ datos }) =>{
    return(
        <table className="table-usuario">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Usuario</th>
                    <th>Nombre</th>
                    <th>Contraseña</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
            {
                datos.map((item, index) => (
                    <tr key={index}>
                        <td>{item.id}</td>
                        <td>{item.username}</td>
                        <td>{item.name}</td>
                        <td>{item.password}</td>
                        <td>
                            <button>Editar</button>
                            <button>Borrar</button>
                        </td>
                    </tr>
                    ))}
            </tbody>
    </table>
    );
};