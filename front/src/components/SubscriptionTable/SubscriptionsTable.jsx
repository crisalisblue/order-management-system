import { DataTable } from "../DataTable/DataTable.jsx";
import {
  getAllSubscriptions,
  deleteSingleSubscription,
} from "../../api/subscriptionAPI.js";
import { useDataFetching } from "../../api/API_Utils.js";

export const SubscriptionsTable = () => {
  const {
    data: subscriptionsData,
    loading,
    error,
  } = useDataFetching(getAllSubscriptions);

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  if (loading) {
    return <div>Loading...</div>;
  }
  if (!subscriptionsData || subscriptionsData.length == 0) {
    return <div>No data...</div>;
  }
  return (
    <DataTable
      data={subscriptionsData}
      keysToShow={Object.keys(...subscriptionsData)}
      itemName="suscripcion"
      editPath="/subscripciones"
      deleteFunction={deleteSingleSubscription}
      hiddenColumns={["id"]}
    />
  );
};
