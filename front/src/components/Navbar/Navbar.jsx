import { Link as RouterLink } from "react-router-dom";
import { useAuthContext } from "../../context/UseAuthContext";
import { useState } from "react";
import "./NavBar.css";

export const NavBar = () => {
  const { logout } = useAuthContext();
  const [open, setOpen] = useState(true);
  const handleOpen = () => {
    setOpen((prevState) => !prevState);
    console.log(open);
  };

  return (
    <nav
      className={`w-full navPointer duration-300 h-full flex flex-col justify-between bg-primary`}
    >
      <button
        className={"flex h-[40px] w-[40px] my-3 mx-auto"}
        onClick={handleOpen}
      >
        <svg
          className={"h-full cursor-pointer "}
          viewBox="0 0 24 24"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
        >
          <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
          <g
            id="SVGRepo_tracerCarrier"
            strokeLinecap="round"
            strokeLinejoin="round"
          ></g>
          <g id="SVGRepo_iconCarrier">
            <path
              d="M20 7L4 7"
              stroke="#eaffff"
              strokeWidth="1.5"
              strokeLinecap="round"
            ></path>
            <path
              d="M20 12L4 12"
              stroke="#eaffff"
              strokeWidth="1.5"
              strokeLinecap="round"
            ></path>
            <path
              d="M20 17L4 17"
              stroke="#eaffff"
              strokeWidth="1.5"
              strokeLinecap="round"
            ></path>
          </g>
        </svg>
      </button>
      <ul className={"flex flex-col justify-evenly items-center w-full h-full"}>
        <li
          className={
            "py-2 w-full hover:bg-mytheme-info duration-300 ease-in-out"
          }
        >
          <RouterLink
            className={"px-3 py-1 flex items-center justify-around"}
            to="/home"
          >
            <svg
              className={"h-[30px] w-[30px] p-0 cursor-pointer"}
              viewBox="0 0 24 24"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
              <g
                id="SVGRepo_tracerCarrier"
                strokeLinecap="round"
                strokeLinejoin="round"
              ></g>
              <g id="SVGRepo_iconCarrier">
                <path
                  d="M22 22L2 22"
                  stroke="#eaffff"
                  strokeWidth="1.5"
                  strokeLinecap="round"
                ></path>
                <path
                  d="M2 11L6.06296 7.74968M22 11L13.8741 4.49931C12.7784 3.62279 11.2216 3.62279 10.1259 4.49931L9.34398 5.12486"
                  stroke="#eaffff"
                  strokeWidth="1.5"
                  strokeLinecap="round"
                ></path>
                <path
                  d="M15.5 5.5V3.5C15.5 3.22386 15.7239 3 16 3H18.5C18.7761 3 19 3.22386 19 3.5V8.5"
                  stroke="#eaffff"
                  strokeWidth="1.5"
                  strokeLinecap="round"
                ></path>
                <path
                  d="M4 22V9.5"
                  stroke="#eaffff"
                  strokeWidth="1.5"
                  strokeLinecap="round"
                ></path>
                <path
                  d="M20 9.5V13.5M20 22V17.5"
                  stroke="#eaffff"
                  strokeWidth="1.5"
                  strokeLinecap="round"
                ></path>
                <path
                  d="M15 22V17C15 15.5858 15 14.8787 14.5607 14.4393C14.1213 14 13.4142 14 12 14C10.5858 14 9.87868 14 9.43934 14.4393M9 22V17"
                  stroke="#eaffff"
                  strokeWidth="1.5"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                ></path>
                <path
                  d="M14 9.5C14 10.6046 13.1046 11.5 12 11.5C10.8954 11.5 10 10.6046 10 9.5C10 8.39543 10.8954 7.5 12 7.5C13.1046 7.5 14 8.39543 14 9.5Z"
                  stroke="#eaffff"
                  strokeWidth="1.5"
                ></path>
              </g>
            </svg>
            <span
              className={`${
                open ? "inline anim-text text-mytheme-accent ml-2" : "gone"
              }`}
            >
              {open && "Home"}
            </span>
          </RouterLink>
        </li>
        <li
          className={
            "py-2 w-full hover:bg-mytheme-info duration-300 ease-in-out"
          }
        >
          <RouterLink
            className={"px-3 py-1 flex items-center justify-around"}
            to="/clientes"
          >
            <svg
              className={"h-[30px] w-[30px] p-0 cursor-pointer"}
              fill="#eaffff"
              version="1.1"
              id="Capa_1"
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 477.297 477.297"
              width="64px"
              height="64px"
            >
              <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
              <g
                id="SVGRepo_tracerCarrier"
                strokeLinecap="round"
                strokeLinejoin="round"
              ></g>
              <g id="SVGRepo_iconCarrier">
                <g>
                  <g>
                    <g>
                      <path d="M42.85,358.075c0-24.138,0-306.758,0-330.917c23.9,0,278.867,0,302.767,0c0,8.542,0,49.44,0,99.722 c5.846-1.079,11.842-1.812,17.99-1.812c3.149,0,6.126,0.647,9.232,0.928V0H15.649v385.233h224.638v-27.158 C158.534,358.075,57.475,358.075,42.85,358.075z"></path>
                      <path d="M81.527,206.842h184.495c1.812-10.16,5.393-19.608,10.095-28.452H81.527V206.842z"></path>
                      <rect
                        x="81.527"
                        y="89.432"
                        width="225.372"
                        height="28.452"
                      ></rect>
                      <path d="M81.527,295.822h191.268c5.112-3.106,10.57-5.63,16.415-7.183c-5.544-6.45-10.095-13.697-13.978-21.269H81.527V295.822z"></path>
                      <path d="M363.629,298.669c41.071,0,74.16-33.197,74.16-74.139c0-40.984-33.09-74.16-74.16-74.16 c-40.898,0-74.009,33.176-74.009,74.16C289.62,265.472,322.731,298.669,363.629,298.669z"></path>
                      <path d="M423.143,310.706H304.288c-21.226,0-38.612,19.457-38.612,43.422v119.33c0,1.316,0.604,2.481,0.69,3.84h194.59 c0.086-1.337,0.69-2.524,0.69-3.84v-119.33C461.733,330.227,444.39,310.706,423.143,310.706z"></path>
                    </g>
                  </g>
                  <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g>
                  <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g> <g> </g>
                  <g> </g>
                </g>
              </g>
            </svg>
            <span
              className={`${
                open ? "inline anim-text text-mytheme-accent ml-2" : "gone"
              }`}
            >
              {open && "Clientes"}
            </span>
          </RouterLink>
        </li>
        <li
          className={
            "py-2 w-full hover:bg-mytheme-info duration-300 ease-in-out"
          }
        >
          <RouterLink
            className={"px-3 py-1 flex items-center justify-around"}
            to="/pedidos"
          >
            <svg
              className={"h-[30px] w-[30px] p-0 cursor-pointer"}
              viewBox="0 0 24 24"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
              <g
                id="SVGRepo_tracerCarrier"
                strokeLinecap="round"
                strokeLinejoin="round"
              ></g>
              <g id="SVGRepo_iconCarrier">
                <path
                  d="M20.2236 12.5257C19.6384 9.40452 19.3458 7.84393 18.2349 6.92196C17.124 6 15.5362 6 12.3606 6H11.6394C8.46386 6 6.87608 6 5.76518 6.92196C4.65428 7.84393 4.36167 9.40452 3.77645 12.5257C2.95353 16.9146 2.54207 19.1091 3.74169 20.5545C4.94131 22 7.17402 22 11.6394 22H12.3606C16.826 22 19.0587 22 20.2584 20.5545C20.9543 19.7159 21.108 18.6252 20.9537 17"
                  stroke="#eaffff"
                  strokeWidth="1.5"
                  strokeLinecap="round"
                ></path>
                <path
                  d="M9 6V5C9 3.34315 10.3431 2 12 2C13.6569 2 15 3.34315 15 5V6"
                  stroke="#eaffff"
                  strokeWidth="1.5"
                  strokeLinecap="round"
                ></path>
              </g>
            </svg>
            <span
              className={`${
                open ? "inline anim-text text-mytheme-accent ml-2" : "gone"
              }`}
            >
              {open && "Pedidos"}
            </span>
          </RouterLink>
        </li>
        <li
          className={
            "py-2 w-full hover:bg-mytheme-info duration-300 ease-in-out"
          }
        >
          <RouterLink
            className={"px-3 py-1 flex items-center justify-around"}
            to="/productos"
          >
            <svg
              className={"h-[30px] w-[30px] p-0 cursor-pointer"}
              fill="#eaffff"
              version="1.1"
              id="Layer_1"
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 512 512"
            >
              <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
              <g
                id="SVGRepo_tracerCarrier"
                strokeLinecap="round"
                strokeLinejoin="round"
              ></g>
              <g id="SVGRepo_iconCarrier">
                <g>
                  <g>
                    <g>
                      <path d="M493.62,128.949c-5.423-9.902-10.547-19.255-14.103-32.225c-1.351-4.929-6.44-7.827-11.37-6.477 c-4.929,1.351-7.828,6.441-6.477,11.37c4.129,15.061,10.022,25.82,15.719,36.222c8.642,15.777,16.105,29.404,16.105,56.474 v299.181H240.579V194.313c0-27.071,7.463-40.696,16.105-56.474c9.035-16.494,19.18-35.017,20.709-69.984h188.342h12.337 c5.11,0,9.253-4.143,9.253-9.253V9.253c0-5.11-4.143-9.253-9.253-9.253H256c-5.11,0-9.253,4.143-9.253,9.253v49.349 c0,5.11,4.143,9.253,9.253,9.253h2.871c-1.408,30.024-9.691,45.16-18.42,61.095c-9.035,16.497-18.379,33.556-18.379,65.363 v308.434c0,5.11,4.143,9.253,9.253,9.253h271.422c5.11,0,9.253-4.143,9.253-9.253V194.313 C512,162.507,502.656,145.448,493.62,128.949z M265.253,18.506H468.82V49.35H265.253V18.506z"></path>
                      <path d="M453.398,364.561c5.11,0,9.253-4.143,9.253-9.253v-136.32c0-5.11-4.143-9.253-9.253-9.253H280.675 c-5.11,0-9.253,4.143-9.253,9.253v172.723c0,5.11,4.143,9.253,9.253,9.253h172.723c5.11,0,9.253-4.143,9.253-9.253 c0-5.11-4.143-9.253-9.253-9.253h-163.47V228.241h154.217v127.067C444.145,360.419,448.288,364.561,453.398,364.561z"></path>
                      <path d="M181.976,259.085H9.253c-2.539,0-4.966,1.042-6.713,2.884c-1.747,1.842-2.661,4.319-2.528,6.855l12.337,234.41 c0.259,4.914,4.319,8.767,9.241,8.767h148.048c4.921,0,8.982-3.853,9.241-8.767l12.337-234.41 c0.133-2.535-0.781-5.013-2.528-6.855C186.942,260.127,184.515,259.085,181.976,259.085z M160.86,493.494H30.37l-8.117-154.217 h110.374c5.11,0,9.253-4.143,9.253-9.253s-4.143-9.253-9.253-9.253H21.591c-0.105,0-0.207,0.012-0.311,0.016l-2.274-43.197 h153.216L160.86,493.494z"></path>
                      <path d="M387.762,351.495c4.476,0.724,8.825-1.901,10.26-6.208l3.559-10.678l3.559,10.678c1.276,3.826,4.849,6.328,8.772,6.328 c0.491,0,0.99-0.039,1.488-0.12c4.48-0.727,7.771-4.596,7.771-9.133v-49.349c0-11.904-9.686-21.59-21.59-21.59h-49.219 l-4.207-4.207c-5.243-5.243-12.214-8.13-19.629-8.13h-0.97c-3.743,0-7.116,2.254-8.549,5.712 c-1.432,3.458-0.64,7.437,2.006,10.083l5.172,5.173l-14.424,12.019c-1.997,1.664-3.203,4.092-3.32,6.689 c-0.118,2.597,0.862,5.124,2.701,6.962l6.169,6.169c1.735,1.736,4.089,2.711,6.543,2.711h6.786v27.759 c0,4.538,3.29,8.407,7.771,9.133c4.476,0.724,8.825-1.901,10.26-6.208l3.559-10.678l3.559,10.678 c1.435,4.306,5.776,6.929,10.26,6.208c4.48-0.727,7.771-4.596,7.771-9.133V326.94h6.169v15.422 C379.991,346.899,383.282,350.768,387.762,351.495z"></path>
                    </g>
                  </g>
                </g>
              </g>
            </svg>
            <span
              className={`${
                open ? "inline anim-text text-mytheme-accent ml-2" : "gone"
              }`}
            >
              {open && "Productos"}
            </span>
          </RouterLink>
        </li>
        <li
          className={
            "py-2 w-full hover:bg-mytheme-info duration-300 ease-in-out"
          }
        >
          <RouterLink
            className={" px-3 py-1 flex items-center justify-around"}
            to="/servicios"
          >
            <svg
              className={"h-[30px] w-[30px] p-0 cursor-pointer"}
              version="1.1"
              id="Layer_1"
              xmlns="http://www.w3.org/2000/svg"
              width="64px"
              height="64px"
              viewBox="0 0 32 32"
              enableBackground="new 0 0 32 32"
              fill="#eaffff"
            >
              <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
              <g
                id="SVGRepo_tracerCarrier"
                strokeLinecap="round"
                strokeLinejoin="round"
              ></g>
              <g id="SVGRepo_iconCarrier">
                <g>
                  <path
                    fill="#eaffff"
                    d="M1.836,23.001c-1.042,0.891-1.672,2.188-1.728,3.559c-0.056,1.37,0.467,2.714,1.435,3.687 c0.935,0.939,2.179,1.457,3.504,1.457c1.424,0,2.778-0.613,3.716-1.681l6.626-7.474l7.739,7.739 c0.916,0.916,2.134,1.421,3.429,1.421c1.296,0,2.514-0.505,3.43-1.421s1.421-2.134,1.421-3.43c0-1.295-0.505-2.513-1.421-3.429 l-8.152-8.152l2.042-2.303c0.604,0.172,1.225,0.259,1.848,0.259c1.903,0,3.659-0.817,4.818-2.242c1.601-1.967,1.8-4.746,0.507-7.08 c-0.291-0.524-1.166-0.503-1.443,0.031l-1.473,2.837c-0.354,0.683-1.248,0.967-1.937,0.612l-1.502-0.782 c-0.341-0.176-0.592-0.475-0.707-0.84c-0.116-0.365-0.082-0.754,0.094-1.094l1.66-3.188c0.133-0.256,0.124-0.556-0.025-0.804 c-0.146-0.242-0.401-0.39-0.75-0.392c-1.903,0-3.66,0.816-4.819,2.241c-1.285,1.579-1.679,3.691-1.079,5.712l-5.612,4.806 L5.739,5.334C6.204,4.658,6.346,3.819,6.08,3.012C5.768,2.065,5.002,1.38,4.033,1.177L1.97,0.744 C1.544,0.653,1.1,0.787,0.793,1.095c-0.309,0.309-0.44,0.749-0.351,1.177l0.433,2.063c0.265,1.266,1.396,2.186,2.69,2.186 c0.537,0,1.038-0.166,1.476-0.47l7.653,7.653L1.836,23.001z M3.565,5.538c-0.706,0-1.521-0.46-1.712-1.373L1.421,2.136 C1.401,2.04,1.431,2.01,1.5,1.941C1.55,1.891,1.615,2,1.73,2c0.011,0,0.022,0,0.034,0l2.063,0.295 C4.444,2.424,4.931,2.724,5.13,3.327C5.319,3.9,5.197,4.507,4.811,4.943L4.643,5.116C4.339,5.386,3.966,5.538,3.565,5.538z M29.279,24.137c0.728,0.727,1.128,1.693,1.128,2.722c0,1.029-0.4,1.996-1.128,2.723c-1.452,1.455-3.99,1.455-5.444,0l-7.782-7.782 l5.117-5.772L29.279,24.137z M20.118,8.242c-0.63-1.786-0.329-3.685,0.805-5.078C21.83,2.05,23.18,1.38,24.712,1.3l-1.518,2.914 c-0.299,0.578-0.356,1.237-0.16,1.857s0.623,1.127,1.2,1.426l1.502,0.782c0.347,0.179,0.733,0.273,1.121,0.273 c0.913,0,1.742-0.503,2.163-1.312l1.315-2.533c0.913,1.905,0.703,4.091-0.568,5.653c-1.347,1.654-3.753,2.286-5.891,1.567 c-0.19-0.063-0.399-0.008-0.534,0.142L8.013,29.36c-0.749,0.854-1.83,1.343-2.966,1.343c-1.057,0-2.05-0.413-2.795-1.162 c-0.783-0.787-1.189-1.832-1.145-2.941s0.534-2.117,1.379-2.839L19.972,8.788C20.129,8.653,20.187,8.437,20.118,8.242z"
                  ></path>
                  <path
                    fill="#eaffff"
                    d="M19.877,21.033l6.026,6.026c0.098,0.098,0.226,0.146,0.354,0.146s0.256-0.049,0.354-0.146 c0.195-0.195,0.195-0.512,0-0.707l-6.026-6.026c-0.195-0.195-0.512-0.195-0.707,0S19.682,20.838,19.877,21.033z"
                  ></path>
                  <path
                    fill="#eaffff"
                    d="M5.065,24.769c-1.103,0-2,0.897-2,2s0.897,2,2,2s2-0.897,2-2S6.168,24.769,5.065,24.769z M5.065,27.769 c-0.552,0-1-0.448-1-1s0.448-1,1-1s1,0.448,1,1S5.617,27.769,5.065,27.769z"
                  ></path>
                </g>
              </g>
            </svg>
            <span
              className={`${
                open ? "inline anim-text text-mytheme-accent ml-2" : "gone"
              }`}
            >
              {open && "Servicios"}
            </span>
          </RouterLink>
        </li>
        <li
          className={
            "py-2 w-full hover:bg-mytheme-info duration-300 ease-in-out"
          }
        >
          <RouterLink
            className={"px-3 py-1 flex items-center justify-around"}
            to="/impuestos"
          >
            <svg
              className={"h-[30px] w-[30px] p-0 cursor-pointer"}
              fill="#eaffff"
              width="64px"
              height="64px"
              viewBox="0 0 1024 1024"
              xmlns="http://www.w3.org/2000/svg"
            >
              <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
              <g
                id="SVGRepo_tracerCarrier"
                strokeLinecap="round"
                strokeLinejoin="round"
              ></g>
              <g id="SVGRepo_iconCarrier">
                <path d="M256 456.533C256 413.866 221.867 384 183.467 384s-72.533 34.133-72.533 72.533c0 42.667 34.133 72.533 72.533 72.533S256 494.933 256 456.533zm-102.4 0c0-17.067 12.8-29.867 29.867-29.867s29.867 12.8 29.867 29.867-12.8 29.867-29.867 29.867-29.867-12.8-29.867-29.867zm221.867 85.334c-38.4 0-72.533 34.133-72.533 72.533 0 42.667 34.133 72.533 72.533 72.533S448 652.8 448 614.4s-34.133-72.533-72.533-72.533zm0 106.666c-17.067 0-29.867-12.8-29.867-29.867s12.8-29.867 29.867-29.867 29.867 12.8 29.867 29.867-17.067 29.867-29.867 29.867zM384 426.667c-8.533-8.533-21.333-8.533-29.867 0L149.333 640c-8.533 8.533-8.533 21.333 0 29.867 4.267 4.267 8.533 4.267 12.8 4.267s12.8-4.267 17.067-8.533L384 452.268c8.533-4.267 8.533-17.067 0-25.6z"></path>
                <path d="M469.333 192H81.066c-34.133 0-59.733 25.6-59.733 59.733v576c0 34.133 25.6 59.733 59.733 59.733h388.267c34.133 0 59.733-25.6 59.733-59.733v-576C533.333 217.6 503.466 192 469.333 192zm21.334 635.733c0 8.533-8.533 17.067-17.067 17.067H81.067C72.534 844.8 64 836.267 64 827.733v-576c0-8.533 8.533-17.067 17.067-17.067h388.267c8.533 0 17.067 8.533 17.067 17.067v576zM994.133 422.4V302.934c0-64-89.6-110.933-213.333-110.933-115.2 0-200.533 42.667-209.067 102.4v490.666c0 8.533 4.267 12.8 8.533 17.067 25.6 46.933 102.4 81.067 204.8 81.067s179.2-34.133 204.8-81.067c4.267-4.267 4.267-8.533 4.267-17.067V422.402zM780.8 234.667c93.867 0 162.133 34.133 166.4 68.267v8.534c-8.533 29.867-76.8 64-166.4 64-98.133 0-170.667-38.4-170.667-68.267 4.267-34.133 76.8-72.533 170.667-72.533zM614.4 371.2c38.4 25.6 98.133 42.667 170.667 42.667 68.267 0 128-17.067 166.4-42.667v55.467c-8.533 29.867-76.8 64-166.4 64-98.133 0-170.667-38.4-170.667-68.267v-51.2zm0 119.467c38.4 25.6 98.133 42.667 170.667 42.667 68.267 0 128-17.067 166.4-42.667V537.6c-8.533 29.867-76.8 64-166.4 64-98.133 0-170.667-38.4-170.667-68.267v-42.667zm0 110.933c38.4 25.6 98.133 42.667 170.667 42.667 68.267 0 128-17.067 166.4-42.667v55.467c-8.533 29.867-76.8 64-166.4 64-98.133 0-170.667-38.4-170.667-68.267v-51.2zm166.4 238.933c-98.133 0-170.667-38.4-170.667-68.267v-51.2c38.4 25.6 98.133 42.667 170.667 42.667 68.267 0 128-17.067 166.4-42.667v55.467c-4.267 34.133-72.533 64-166.4 64z"></path>
              </g>
            </svg>
            <span className={`${open ? "inline anim-text" : "gone"}`}>
              {open && (
                <span className="anim-text text-mytheme-accent ml-2">
                  Impuestos
                </span>
              )}
            </span>
          </RouterLink>
        </li>
        <li
          className={
            "py-2 w-full hover:bg-mytheme-info duration-300 ease-in-out"
          }
        >
          <RouterLink
            className={"px-3 py-1 flex items-center justify-around"}
            to="/usuarios"
          >
            <svg
              className={"h-[30px] w-[30px] p-0 cursor-pointer"}
              fill="#eaffff"
              version="1.1"
              id="Layer_1"
              xmlns="http://www.w3.org/2000/svg"
              width="64px"
              height="64px"
              viewBox="924 578 200 200"
              enableBackground="new 924 578 200 200"
            >
              <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
              <g
                id="SVGRepo_tracerCarrier"
                strokeLinecap="round"
                strokeLinejoin="round"
              ></g>
              <g id="SVGRepo_iconCarrier">
                <g>
                  <g>
                    <path d="M984.585,638.942c0,13.999-9.609,25.348-21.462,25.348c-11.852,0-21.459-11.349-21.459-25.348 c0-13.998,9.607-25.346,21.459-25.346C974.976,613.596,984.585,624.944,984.585,638.942z"></path>
                    <path d="M987.585,683.641c1.55-0.945,3.265-1.561,5.041-1.855c-3.606-5.088-6.161-10.546-7.637-17.078 c-0.404-2.387-3.672-2.667-6.102-0.687c-4.546,3.706-9.849,6.186-15.765,6.186c-6.03,0-11.577-2.399-16.024-6.414 c-1.419-1.282-3.51-1.476-5.143-0.479c-8.443,5.158-14.834,13.344-17.622,23.067c-0.749,2.605-0.223,5.42,1.411,7.588 c1.636,2.166,4.192,3.443,6.906,3.443h38.668C975.947,692.072,981.41,687.41,987.585,683.641z"></path>
                  </g>
                  <g>
                    <path d="M1063.416,638.942c0,13.999,9.608,25.348,21.461,25.348c11.854,0,21.46-11.349,21.46-25.348 c0-13.998-9.606-25.346-21.46-25.346C1073.024,613.596,1063.416,624.944,1063.416,638.942z"></path>
                    <path d="M1060.415,683.641c-1.55-0.945-3.266-1.561-5.041-1.855c3.606-5.088,6.161-10.546,7.637-17.078 c0.405-2.387,3.673-2.667,6.103-0.687c4.546,3.706,9.848,6.186,15.764,6.186c6.029,0,11.577-2.399,16.025-6.414 c1.419-1.282,3.509-1.476,5.142-0.479c8.444,5.158,14.836,13.344,17.622,23.067c0.748,2.605,0.223,5.42-1.41,7.588 c-1.637,2.166-4.192,3.443-6.905,3.443h-38.67C1072.053,692.072,1066.591,687.41,1060.415,683.641z"></path>
                  </g>
                  <g>
                    <path d="M1082.475,725.451c-4.198-14.654-13.72-27.045-26.326-34.992c-2.487-1.566-5.715-1.313-7.921,0.631 c-6.766,5.959-15.138,9.506-24.228,9.506c-9.269,0-17.791-3.686-24.626-9.855c-2.182-1.971-5.393-2.268-7.902-0.734 c-12.977,7.924-22.799,20.504-27.082,35.445c-1.151,4.008-0.344,8.328,2.166,11.662c2.516,3.33,6.443,5.291,10.615,5.291h92.523 c4.173,0,8.103-1.955,10.618-5.291C1082.823,733.779,1083.626,729.463,1082.475,725.451z"></path>
                    <path d="M1056.981,652.547c0,21.513-14.766,38.955-32.981,38.955c-18.214,0-32.979-17.442-32.979-38.955 c0-21.515,14.765-38.951,32.979-38.951C1042.216,613.596,1056.981,631.033,1056.981,652.547z"></path>
                  </g>
                </g>
              </g>
            </svg>
            <span
              className={`${
                open ? "inline anim-text text-mytheme-accent ml-2" : "gone"
              }`}
            >
              {open && "Usuarios"}
            </span>
          </RouterLink>
        </li>
        <li
          className={
            "py-2 w-full hover:bg-mytheme-info duration-300 ease-in-out"
          }
        >
          <RouterLink
            className={"px-2 py-1 flex items-center justify-around"}
            to="/login"
            onClick={logout}
          >
            <svg
              className={"h-[30px] w-[30px] p-0 cursor-pointer"}
              width="64px"
              height="64px"
              viewBox="0 0 24 24"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
              <g
                id="SVGRepo_tracerCarrier"
                strokeLinecap="round"
                strokeLinejoin="round"
              ></g>
              <g id="SVGRepo_iconCarrier">
                <path
                  d="M21 12L13 12"
                  stroke="#eaffff"
                  strokeWidth="2"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                ></path>
                <path
                  d="M18 15L20.913 12.087V12.087C20.961 12.039 20.961 11.961 20.913 11.913V11.913L18 9"
                  stroke="#eaffff"
                  strokeWidth="2"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                ></path>
                <path
                  d="M16 5V4.5V4.5C16 3.67157 15.3284 3 14.5 3H5C3.89543 3 3 3.89543 3 5V19C3 20.1046 3.89543 21 5 21H14.5C15.3284 21 16 20.3284 16 19.5V19.5V19"
                  stroke="#eaffff"
                  strokeWidth="2"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                ></path>
              </g>
            </svg>
            <span
              className={`${
                open ? "inline anim-text text-mytheme-accent ml-2" : "gone"
              }`}
            >
              {open && "Cerrar  Sesión"}
            </span>
          </RouterLink>
        </li>
      </ul>
    </nav>
  );
};
