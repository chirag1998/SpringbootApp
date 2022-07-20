import * as React from "react";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import { Link, useNavigate } from "react-router-dom";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { useState } from "react";
import axios from "axios";

const theme = createTheme();

export default function SignIn() {
  const [emailid, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [emailidError, setEmailidError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);

  let navigate = useNavigate();

  // React.useEffect(() => {
  //   emailid.trim().length === 0
  //     ? setEmailidError(true)
  //     : setEmailidError(false);
  // }, [emailid]);

 

  const handleSubmit = (event) => {

    event.preventDefault();
    let emailError = false;
    let passError = false;

    emailError = emailid.trim().length === 0 ? true : false;
    passError = password.trim().length === 0 ? true : false;

    let er = false;

    if (emailError || passError) er = true;
    else er = false;

    emailError && setEmailidError (true)
    passError && setPasswordError (true)


    if (!er) {
      let data = {
        emailid,
        password,
      };

      axios
        .post("http://localhost:8080/login", data)
        .then((response) => {
          if (response.status === 200) {
            navigate("dashboard");
          }
        })
        .catch((error) => {
          console.error();
        });
    }
  };

  return (
    <ThemeProvider theme={theme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign in
          </Typography>
          <Box
            component="form"
            onSubmit={handleSubmit}
            noValidate
            sx={{ mt: 1 }}
          >
            <TextField
              margin="normal"
              required
              fullWidth
              id="email"
              label="Email Address"
              name="email"
              autoComplete="email"
              value={emailid}
              onChange={(e) => {
                setEmail(e.target.value);
                setEmailidError(false);
              }}
              onBlur={(e) => {
                if (!e.target.value) setEmailidError(true);
              }}
              autoFocus
              error={emailidError}
              helperText={"enter email"}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="password"
              label="Password"
              type="password"
              id="password"
              value={password}
              onChange={(e) => {
                setPassword(e.target.value);
                setPasswordError(false);
              }}
              onBlur={(e) => {
                if (!e.target.value) setPasswordError(true);
              }}
              autoComplete="current-password"
              error={passwordError}
              helperText={"enter password"}
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign In
            </Button>
            <Grid container>
              <Grid item xs>
                <Link
                  to="#"
                  variant="body2"
                  style={{ textDecoration: "none", fontWeight: "bold" }}
                >
                  Forgot password?
                </Link>
              </Grid>
              <Grid item>
                <Link
                  to="/#"
                  variant="body2"
                  style={{ textDecoration: "none", fontWeight: "bold" }}
                >
                  SignUp here
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}
