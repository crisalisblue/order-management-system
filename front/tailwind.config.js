/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  daisyui: {
    themes: [
      {
        mytheme: {
          primary: "#272835",

          secondary: "#e83e6b",

          accent: "#ef28db",

          neutral: "#272835",

          "base-100": "#392c4e",

          info: "#58caf3",

          success: "#17726b",

          warning: "#9c840d",

          error: "#e6655c",
        },
      },
      "coffee",
    ],
  },
  plugins: [require("@tailwindcss/typography"), require("daisyui")],
};
