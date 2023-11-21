export const DeleteModal = ({ itemID, onDelete }) => {
  const handleDelete = () => {
    onDelete(itemID);
  };

  return (
    <>
      <button className="" onClick={handleDelete}>
        🗑
      </button>
    </>
  );
};
