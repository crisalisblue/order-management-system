import { DeleteModal } from "../DeleteModal";
export const DataTable = ({ data }) => {
  return (
    <table className="table table-zebra table-xs">
      <thead>
        <tr>
          <th>Username</th>
          <th>Name</th>
          <th>Password</th>
        </tr>
      </thead>
      <tbody>
        {data.map((item, index) => (
          <tr key={index}>
            <td>{item.username}</td>
            <td>{item.name}</td>
            <td>{item.password}</td>
            <td>
              <a href={`/usuarios/${item.id}/editar`}>
                <button className="btn">Editar</button>
              </a>
              <DeleteModal itemId={item.id}></DeleteModal>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};
