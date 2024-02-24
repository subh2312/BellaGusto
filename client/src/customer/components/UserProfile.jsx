import { Grid, Paper } from "@mui/material";
import React from "react";
import styled from "@emotion/styled";
const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: "center",
  color: theme.palette.text.secondary,
}));
const UserProfile = () => {
  return (
    <div>
      <Grid container spacing={2}>
        <Grid xs={8}>
          <Item>xs=8</Item>
        </Grid>
        <Grid xs={4}>
          <Item>xs=4</Item>
        </Grid>
        <Grid xs={4}>
          <Item>xs=4</Item>
        </Grid>
        <Grid xs={8}>
          <Item>xs=8</Item>
        </Grid>
      </Grid>
    </div>
  );
};

export default UserProfile;
