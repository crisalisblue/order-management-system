import { DeleteModal } from "../DeleteModal/DeleteModal";
export const DataTable = ({ data }) => {
  return (
    <table className="table table-zebra table-xs bg-primary text-primary">
      <thead>
        <tr className="bg-secondary">
          <th>Username</th>
          <th>Name</th>
          <th>Password</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        {data.map((item, index) => (
          <tr key={index}>
            <td>{item.username}</td>
            <td>{item.name}</td>
            <td>{item.password}</td>
            <td className="flex justify-evenly">
              <a href={`/usuarios/${item.id}/editar`}>
                <button className="btn btn-success">Editar</button>
              </a>
              <DeleteModal itemId={item.id}></DeleteModal>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};
