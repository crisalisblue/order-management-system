/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors: {
        mytheme: {
          primary: "#111827",
          secondary: "#ea580c",
          accent: "#10b981",
          neutral: "#d1d5db",
          "base-100": "#1d232a",
          info: "#3abff8",
          success: "#36d399",
          warning: "#fbbd23",
          error: "#f87272",
        },
      },
    },
  },
  plugins: [require("daisyui")],
};
