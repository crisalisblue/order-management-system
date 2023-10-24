export const DataTable = ({ data }) => {
  return (
    <table className="table-auto">
      <thead>
        <tr>
          <th>ID</th>
          <th>Username</th>
          <th>Name</th>
          <th>Password</th>
        </tr>
      </thead>
      <tbody>
        {data.map((item, index) => (
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