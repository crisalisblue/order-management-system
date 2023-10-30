import { Link as RouterLink, useLocation } from "react-router-dom";
import "./DynamicLink.css";
export const DynamicLink = ({ name, expectedUrl }) => {
  //La unica magia de este link es que se deshabilita en funcion del path
  const location = useLocation();
  const shouldBeDisabled = location.pathname === expectedUrl;
  console.dir(
    `Location Pathname: ${location.pathname}, Expected URL: ${expectedUrl}, Should Be Disabled: ${shouldBeDisabled}`
  );

  return (
    <RouterLink
      className={`tab tab-lg tab-lifted ${
        shouldBeDisabled ? "tab-active tab-unclickable" : "text-base-100"
      } w-1/2`}
      to={shouldBeDisabled ? location.pathname : expectedUrl}
      onClick={shouldBeDisabled ? (e) => e.preventDefault() : null}
    >
      {name}
    </RouterLink>
  );
};
