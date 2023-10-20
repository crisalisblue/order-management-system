import { Controller } from "react-hook-form";
import TextField from "@mui/material/TextField";
export const CrudTextField = ({ label, control, isDisabled, defaultValue }) => {
  return (
    <Controller
      name={label}
      control={control}
      render={({
        field: { onChange, value },
        fieldState: { error },
        formState,
      }) => (
        <TextField
          helperText={error ? error.message : null}
          size="small"
          error={!!error}
          onChange={onChange}
          value={value || defaultValue || ""}
          label={label || "label"}
          disabled={isDisabled}
          variant="outlined"
        />
      )}
    />
  );
};
