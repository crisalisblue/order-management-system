import { Outlet, Link as RouterLink, useLocation } from "react-router-dom";

export const Subscripciones = () => {
  const { pathname } = useLocation();

  return (
    <article className="prose grid min-w-full">
      <h1 className="text-primary text-center m-0 p-0">Subscripciones</h1>

      <span
        className={`w-5/6 m-auto  ${
          pathname === "/subscripciones/nuevo" ? "tab-active" : ""
        } w-1/2`}
      >
        <RouterLink
          to={
            pathname === "/subscripciones"
              ? "/subscripciones/nuevo"
              : "/subscripciones"
          }
        ></RouterLink>
      </span>

      <Outlet />
    </article>
  );
};