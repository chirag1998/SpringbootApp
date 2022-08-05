import {
  Box,
  Button,
  Checkbox,
  Container,
  FormControl,
  FormControlLabel,
  FormGroup,
  FormLabel,
  Grid,
  Radio,
  RadioGroup,
  TextField,
  Toolbar,
  Typography,
} from "@mui/material";

import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import Select from "react-select";
import countryList from "react-select-country-list";
import { useMemo } from "react";
import { DesktopDatePicker } from "@mui/x-date-pickers";
const initialValues = {
  mobile: "",
  dob: "",
  fgender: "female",
  fhobbies: [],
  country: "",
  profile: null,
};

export default function BasicDetails() {
  let location = useLocation();
  const [data, setdata] = useState({});

  const [date, setDate] = React.useState(new Date().toLocaleDateString());

  const [nation, setNation] = useState("");
  const options = useMemo(() => countryList().getData(), []);

  const [image, setimage] = useState(null);
  // const [gender, setGender] = useState("female");
  const [formData, setFormData] = useState(initialValues);
  const { mobile, dob, fgender, fhobbies, country, profile } = formData;

  const uploadHandler = (e) => {
    console.log("uploaded &&&&&s");
    setimage(URL.createObjectURL(e.target.files[0]));
    setFormData({
      ...formData,
      profile: URL.createObjectURL(e.target.files[0]),
    });
  };

  const dropHandler = (newValue) => {
    setNation(newValue);
    setFormData({ ...formData, country: newValue.label });
  };

  const handleDateChange = (newValue) => {
    setDate(newValue);
    setFormData({ ...formData, dob: newValue });
  };
  const handleGenderChange = (event) => {
    // setGender(event.target.value);
    setFormData({ ...formData, fgender: event.target.value });
  };

  const [hobbies, setHobbies] = React.useState({
    trading: false,
    coding: false,
    design: false,
    reading: false,
  });

  const handleChange = (event) => {
    setHobbies({
      ...hobbies,
      [event.target.name]: event.target.checked,
    });
    setFormData({ ...formData, fhobbies: hobbies });
  };

  const { trading, coding, design, reading } = hobbies;

  useEffect(() => {
    setdata(location.state.data);
  }, []);

  const onChange = (e) => {
    console.log(e.target.id);
    const { value, id } = e.target;
    setFormData({ ...formData, [id]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(formData);
  };
  return (
    <>
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          height: "100vh",
          overflow: "auto",
        }}
      >
        <Toolbar />
        <form onSubmit={handleSubmit}>
          <Container sx={{ display: "flex" }} spacing={2}>
            <Box sx={{ mt: 8, ml: 4 }}>
              <Grid container spacing={2}>
                <Grid item xs={6}>
                  <TextField
                    autoComplete="firstName"
                    name="firstName"
                    value={data.firstName}
                    required
                    fullWidth
                    id="firstName"
                    label="First Name"
                    disabled="true"
                  ></TextField>
                </Grid>
                <Grid item xs={6}>
                  <TextField
                    autoComplete="lastName"
                    name="lastName"
                    value={data.lastName}
                    required
                    fullWidth
                    id="lastName"
                    label="Last Name"
                    disabled="true"
                  ></TextField>
                </Grid>
              </Grid>
              <Grid container spacing={1}>
                <Grid item xs={12} sx={{ mt: 4 }}>
                  <TextField
                    autoComplete="email"
                    name="email"
                    value={data.email}
                    required
                    fullWidth
                    id="email"
                    label="Email"
                    disabled="true"
                  ></TextField>
                </Grid>
              </Grid>

              <Grid container spacing={1}>
                <Grid item xs={12} sx={{ mt: 4 }}>
                  <TextField
                    autoComplete="id"
                    name="id"
                    value={data.id}
                    required
                    fullWidth
                    id="id"
                    label="Employee ID"
                    disabled="true"
                  ></TextField>
                </Grid>
              </Grid>
              <Grid container spacing={1}>
                <Grid item xs={12} sx={{ mt: 4 }}>
                  <TextField
                    autoComplete="joiningdate"
                    name="joiningdate"
                    value={data.date}
                    fullWidth
                    id="joiningdate"
                    label="Joining Date"
                    disabled="true"
                  ></TextField>
                </Grid>
              </Grid>
              <Grid container spacing={2}>
                <Grid item xs={12} sx={{ mt: 4 }}>
                  <TextField
                    autoComplete="mobile"
                    name="mobile"
                    fullWidth
                    id="mobile"
                    label="Mobile"
                    value={mobile}
                    onChange={(e) => onChange(e)}
                  ></TextField>
                </Grid>
              </Grid>
            </Box>
            <Box sx={{ mt: 8, ml: 12 }}>
              <Grid container spacing={1}>
                <Grid item xs={12}>
                  <LocalizationProvider dateAdapter={AdapterDateFns}>
                    <DesktopDatePicker
                      label="DOB"
                      inputFormat="MM-dd-yyyy"
                      value={date}
                      onChange={handleDateChange}
                      renderInput={(params) => <TextField {...params} />}
                    />
                  </LocalizationProvider>
                </Grid>
                <Grid item xs={12} sx={{ mt: 2 }}>
                  <FormControl>
                    <FormLabel id="demo-radio-buttons-group-label">
                      Gender
                    </FormLabel>
                    <RadioGroup
                      row
                      aria-labelledby="demo-radio-buttons-group-label"
                      defaultValue="female"
                      name="radio-buttons-group"
                      value={fgender}
                      onChange={handleGenderChange}
                    >
                      <FormControlLabel
                        value="female"
                        control={<Radio />}
                        label="Female"
                      />
                      <FormControlLabel
                        value="male"
                        control={<Radio />}
                        label="Male"
                      />
                      <FormControlLabel
                        value="other"
                        control={<Radio />}
                        label="Other"
                      />
                    </RadioGroup>
                  </FormControl>
                </Grid>
                <Grid item xs={12} sx={{ mt: 2 }}>
                  <FormControl component="fieldset" variant="standard">
                    <FormLabel component="legend">Hobbies</FormLabel>
                    <FormGroup>
                      <Grid container spacing={2}>
                        <Grid item xs={6}>
                          <FormControlLabel
                            control={
                              <Checkbox
                                checked={trading}
                                onChange={handleChange}
                                name="trading"
                              />
                            }
                            label="Trading"
                          />
                        </Grid>
                        <Grid item xs={6}>
                          <FormControlLabel
                            control={
                              <Checkbox
                                checked={coding}
                                onChange={handleChange}
                                name="coding"
                              />
                            }
                            label="Coding"
                          />
                        </Grid>
                      </Grid>
                      <Grid container spacing={2}>
                        <Grid item xs={6}>
                          <FormControlLabel
                            control={
                              <Checkbox
                                checked={design}
                                onChange={handleChange}
                                name="design"
                              />
                            }
                            label="UI/UX Design"
                          />
                        </Grid>
                        <Grid item xs={6}>
                          <FormControlLabel
                            control={
                              <Checkbox
                                checked={reading}
                                onChange={handleChange}
                                name="reading"
                              />
                            }
                            label="Reading"
                          />
                        </Grid>
                      </Grid>
                    </FormGroup>
                  </FormControl>
                </Grid>
                <Grid item xs={6} sx={{ mt: 2 }}>
                  <Typography>Select Country</Typography>
                  <Select
                    options={options}
                    value={nation}
                    onChange={dropHandler}
                  />
                </Grid>
              </Grid>
              <Grid>
                <Grid item xs={6} sx={{ mt: 3 }}>
                  <Button variant="outlined" component="label">
                    Upload Profile
                    <input type="file" hidden onChange={uploadHandler}></input>
                  </Button>
                </Grid>
              </Grid>
              <Grid item xs={3} sx={{ mt: -54, ml: 50 }}>
                {image && (
                  <>
                    <img
                      src={image}
                      alt="preview"
                      height="150px"
                      width="150px"
                      style={{ display: "inline" }}
                    ></img>
                    <Typography>Profile Preview</Typography>
                  </>
                )}
              </Grid>
            </Box>
          </Container>
          <Button
            type="submit"
            variant="contained"
            sx={{ mt: 5, ml: 50, width: "200px" }}
          >
            Submit
          </Button>
        </form>
      </Box>
    </>
  );
}
